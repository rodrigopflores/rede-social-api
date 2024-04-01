package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.controller;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.AnswerFriendRequestRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.ChangeUserInfoRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserProfileResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/privado/user")
public class UserPrivateController {

    @Autowired
    private UserService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserStandardResponse getStandardUser() {
        return service.getStandardUser();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void changeUserInformation(@RequestBody ChangeUserInfoRequest request) {
        service.changeUserInfo(request);
    }


    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileResponse getUserProfile(@PathVariable Integer userId) {
        return service.getUserProfile(userId);
    }

    @GetMapping("/friends")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserStandardResponse> getUserFriendsPage(@RequestParam Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getUserFriendsPage(pageable);
    }

    @DeleteMapping("/friends")
    @ResponseStatus(HttpStatus.OK)
    public void endFriendship(@RequestParam Integer id) {
        service.endFriendship(id);
    }

    @PostMapping("/friend-request")
    @ResponseStatus(HttpStatus.OK)
    public void sendFriendRequest(@RequestParam Integer id) {
        service.sendFriendRequest(id);
    }

    @GetMapping("/friend-request")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserStandardResponse> getFriendRequests(@RequestParam Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getFriendRequests(pageable);
    }

    @PutMapping("/friend-request")
    @ResponseStatus(HttpStatus.OK)
    public void answerFriendRequest(@Valid @RequestBody AnswerFriendRequestRequest request) {
        service.answerFriendRequest(request);
    }

    @DeleteMapping("/friend-request")
    @ResponseStatus(HttpStatus.OK)
    public void cancelFriendRequest(@RequestParam Integer id) {
        service.cancelFriendRequest(id);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserProfileResponse> searchUser(@RequestParam Integer page, Integer size, String query) {
        Pageable pageable = PageRequest.of(page, size);
        return service.searchUser(query, pageable);
    }


}
