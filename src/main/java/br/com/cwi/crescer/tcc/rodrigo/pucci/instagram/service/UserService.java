package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.exception.BusinessValidationException;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.UserMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.UserRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.AnswerFriendRequestRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.ChangeUserInfoRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateUserRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var teste= repository.findByEmail(email);
              return teste.orElseThrow(() -> new UsernameNotFoundException("User " + email + " not found"));
    }


    public UserStandardResponse createUser(CreateUserRequest request) {
        User user = mapper.toDomain(request);
        System.out.println(user);
        user.setPassword(encoder.encode(request.getPassword()));
        System.out.println(user.getPassword());
        user = repository.save(user);

        return mapper.toUserStandardResponse(user);
    }

    public User getUser() {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return repository.findByEmail(loggedUser.getEmail())
                .orElseThrow(() ->  new BusinessValidationException("User not found"));
    }

    public UserStandardResponse getStandardUser() {
        User user = getUser();
        return mapper.toUserStandardResponse(user);
    }

    public User getValidatedUserById(Integer id) {
        User foundUser = repository.findById(id).orElse(null);
        if (foundUser == null) {
            throw new BusinessValidationException("User not found");
        }

        return foundUser;
    }

    public Page<UserStandardResponse> getUserFriendsPage(Pageable pageable) {
        if (pageable.getPageSize() > 50) {
            throw new BusinessValidationException("Too many pages");
        }
        User user = getUser();
        Page<User> friendsPage = repository.findByFriendOfIdOrderByFirstNameAsc(user.getId(), pageable);
        List<UserStandardResponse> friendsPageAsList = friendsPage.get().map(friend -> mapper.toUserStandardResponse(friend)).collect(Collectors.toList());
        return new PageImpl<>(friendsPageAsList, pageable, friendsPage.getTotalElements());
    }

    public void sendFriendRequest(Integer id) {
        User user = getUser();
        User friend = getValidatedUserById(id);
        user.getFriendRequestsSent().add(friend);
        repository.save(user);
    }

    public Page<UserStandardResponse> getFriendRequests(Pageable pageable) {
        User user = getUser();
        Page<User> friendRequestsReceivedPage = repository.findByFriendRequestsSentId(user.getId(), pageable);

        List<UserStandardResponse> friendRequestsPageAsList = friendRequestsReceivedPage.get().map(friend -> mapper.toUserStandardResponse(friend)).collect(Collectors.toList());
        return new PageImpl<>(friendRequestsPageAsList, pageable, friendRequestsReceivedPage.getTotalElements());
    }

    public void answerFriendRequest(AnswerFriendRequestRequest request) {
        User user = getUser();
        User friend = getValidatedUserById(request.getFriendId());
        boolean friendRequestDoesntExist = !friend.getFriendRequestsSent().contains(user);
        if (friendRequestDoesntExist) {
            throw new BusinessValidationException("Friend request not found");
        }
        if (request.isAccepted()) {
            user.getFriends().add(friend);
            friend.getFriends().add(user);
            friend.getFriendRequestsSent().remove(user);
        } else {
            friend.getFriendRequestsSent().remove(user);
        }

        repository.save(user);
        repository.save(friend);
    }


    public void endFriendship(Integer id) {
        User user = getUser();
        User friend = getValidatedUserById(id);
        boolean notFriends = !user.getFriends().contains(friend);

        if (notFriends) {
            throw new BusinessValidationException("Friendship not found");
        }

        user.getFriends().remove(friend);
        friend.getFriends().remove(user);

        repository.save(user);
        repository.save(friend);
    }

    public boolean areFriends(Integer id1, Integer id2) {
        User user1 = repository.findById(id1).orElse(null);
        User user2 = repository.findById(id2).orElse(null);
        if (user1 == null || user2 == null) return false;
        return user1.getFriends().contains(user2);
    }

    public User getUserById(Integer id) {
        return repository.findById(id).orElse(null);
    }

//    public LoginResponse login(LoginRequest request) {
//        return securityApiService.login(request);
//    }

    public Page<UserProfileResponse> searchUser(String request, Pageable pageable) {
        User user = getUser();
        Page<User> resultPage = repository.searchUserByString(request, user.getId(), pageable);
        List<UserProfileResponse> resultList = resultPage.get().map(foundUser -> {
            UserProfileResponse response = mapper.toUserProfileResponse(foundUser);
            boolean areFriends = user.getFriends().contains(foundUser);
            boolean requestSent = user.getFriendRequestsSent().contains(foundUser);
            response.setAreFriends(areFriends);
            response.setRequestSent(requestSent);
            return response;
        }).collect(Collectors.toList());

        return new PageImpl<>(resultList, pageable, resultPage.getTotalElements());
    }

    public UserProfileResponse getUserProfile(Integer userId) {
        User user = getUser();
        User profileUser = getValidatedUserById(userId);
        boolean areFriends = user.getFriends().contains(profileUser);
        boolean sentRequest = user.getFriendRequestsSent().contains(profileUser);
        UserProfileResponse response = mapper.toUserProfileResponse(profileUser);
        response.setAreFriends(areFriends);
        response.setRequestSent(sentRequest);
        response.setNumberOfFriends(profileUser.getFriends().size());
        response.setNumberOfPosts(profileUser.getPosts().size());
        return response;
    }

    public void cancelFriendRequest(Integer id) {
        User user = getUser();
        User friend = getValidatedUserById(id);
        user.getFriendRequestsSent().remove(friend);
        repository.save(user);
    }

    public void changeUserInfo(ChangeUserInfoRequest request) {
        User user = getUser();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String nickName = request.getNickName();
        String profilePic = request.getProfilePic();

        if (!firstName.isEmpty()) {
            user.setFirstName(firstName);
        }
        if (!lastName.isEmpty()) {
            user.setLastName(lastName);
        }
        if (!nickName.isEmpty()) {
            user.setNickName(nickName);
        }
        if (!profilePic.isEmpty()) {
            user.setProfilePic(profilePic);
        }

        repository.save(user);
    }
}
