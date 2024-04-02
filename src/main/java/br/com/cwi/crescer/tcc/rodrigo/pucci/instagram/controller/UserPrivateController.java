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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for private user operations. All endpoints in this controller require authentication.
 */
@RestController
@RequestMapping("/privado/user")
public class UserPrivateController {

  @Autowired private UserService service;

  /**
   * Get the standard user information.
   *
   * @return UserStandardResponse object containing user information.
   */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public UserStandardResponse getStandardUser() {
    return service.getStandardUser();
  }

  /**
   * Change the user's information.
   *
   * @param request ChangeUserInfoRequest object containing the new user information.
   */
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public void changeUserInformation(@RequestBody ChangeUserInfoRequest request) {
    service.changeUserInfo(request);
  }

  /**
   * Get a user's profile by their ID.
   *
   * @param userId The ID of the user.
   * @return UserProfileResponse object containing the user's profile information.
   */
  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public UserProfileResponse getUserProfile(@PathVariable Integer userId) {
    return service.getUserProfile(userId);
  }

  /**
   * Get a page of the user's friends.
   *
   * @param page The page number.
   * @param size The size of the page.
   * @return A Page object containing UserStandardResponse objects for each friend.
   */
  @GetMapping("/friends")
  @ResponseStatus(HttpStatus.OK)
  public Page<UserStandardResponse> getUserFriendsPage(@RequestParam Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return service.getUserFriendsPage(pageable);
  }

  /**
   * End a friendship with another user.
   *
   * @param id The ID of the friend to end the friendship with.
   */
  @DeleteMapping("/friends")
  @ResponseStatus(HttpStatus.OK)
  public void endFriendship(@RequestParam Integer id) {
    service.endFriendship(id);
  }

  /**
   * Send a friend request to another user.
   *
   * @param id The ID of the user to send the friend request to.
   */
  @PostMapping("/friend-request")
  @ResponseStatus(HttpStatus.OK)
  public void sendFriendRequest(@RequestParam Integer id) {
    service.sendFriendRequest(id);
  }

  /**
   * Get a page of the user's friend requests.
   *
   * @param page The page number.
   * @param size The size of the page.
   * @return A Page object containing UserStandardResponse objects for each friend request.
   */
  @GetMapping("/friend-request")
  @ResponseStatus(HttpStatus.OK)
  public Page<UserStandardResponse> getFriendRequests(@RequestParam Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return service.getFriendRequests(pageable);
  }

  /**
   * Answer a friend request.
   *
   * @param request AnswerFriendRequestRequest object containing the answer to the friend request.
   */
  @PutMapping("/friend-request")
  @ResponseStatus(HttpStatus.OK)
  public void answerFriendRequest(@Valid @RequestBody AnswerFriendRequestRequest request) {
    service.answerFriendRequest(request);
  }

  /**
   * Cancel a friend request.
   *
   * @param id The ID of the friend request to cancel.
   */
  @DeleteMapping("/friend-request")
  @ResponseStatus(HttpStatus.OK)
  public void cancelFriendRequest(@RequestParam Integer id) {
    service.cancelFriendRequest(id);
  }

  /**
   * Search for users.
   *
   * @param page The page number.
   * @param size The size of the page.
   * @param query The search query.
   * @return A Page object containing UserProfileResponse objects for each user found.
   */
  @GetMapping("/search")
  @ResponseStatus(HttpStatus.OK)
  public Page<UserProfileResponse> searchUser(
      @RequestParam Integer page, Integer size, String query) {
    Pageable pageable = PageRequest.of(page, size);
    return service.searchUser(query, pageable);
  }
}
