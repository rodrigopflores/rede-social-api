package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.controller;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for private post operations.
 * All endpoints in this controller require authentication.
 */
@RestController
@RequestMapping("privado/post")
public class PostPrivateController {

  @Autowired private PostService service;

  /**
   * Create a new post.
   * @param request CreatePostRequest object containing the new post data.
   * @return PostResponse object containing the created post data.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
    return service.createPost(request);
  }

  /**
   * Get a user's posts by their ID.
   * @param userId The ID of the user.
   * @param page The page number.
   * @param size The size of the page.
   * @return A Page object containing PostResponse objects for each post.
   */
  @GetMapping("/{userId}")
  @ResponseStatus(HttpStatus.OK)
  public Page<PostResponse> getUserPosts(
          @PathVariable Integer userId, @RequestParam Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return service.getUserPosts(userId, pageable);
  }

  /**
   * Like a post.
   * @param postId The ID of the post to like.
   */
  @PostMapping("/{postId}/like")
  @ResponseStatus(HttpStatus.OK)
  public void likePost(@PathVariable Integer postId) {
    service.likePost(postId);
  }

  /**
   * Get a user's feed.
   * @param page The page number.
   * @param size The size of the page.
   * @return A Page object containing PostResponse objects for each post in the feed.
   */
  @GetMapping("feed")
  @ResponseStatus(HttpStatus.OK)
  public Page<PostResponse> getUserFeed(@RequestParam Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return service.getUserFeed(pageable);
  }
}