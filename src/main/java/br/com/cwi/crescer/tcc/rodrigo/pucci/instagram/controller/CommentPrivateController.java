package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.controller;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service.CommentService;
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
 * This is a controller class for handling private comments in the Instagram application. It
 * provides endpoints for creating a comment and fetching comments for a specific post.
 */
@RestController
@RequestMapping("/privado/comment")
public class CommentPrivateController {

  // Service class for Comment related operations
  @Autowired private CommentService service;

  /**
   * This method is a POST endpoint for creating a new comment. It takes a CreateCommentRequest
   * object as input and returns a CommentResponse object.
   *
   * @param request CreateCommentRequest object containing the details of the comment to be created.
   * @return CommentResponse object containing the details of the created comment.
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentResponse createComment(@Valid @RequestBody CreateCommentRequest request) {
    return service.createComment(request);
  }

  /**
   * This method is a GET endpoint for fetching comments of a specific post. It takes the post ID,
   * page number and size as input and returns a Page of CommentResponse objects.
   *
   * @param postId The ID of the post for which comments are to be fetched.
   * @param page The page number of the comments to be fetched.
   * @param size The size of the page to be fetched.
   * @return Page of CommentResponse objects containing the details of the comments.
   */
  @GetMapping("/{postId}")
  @ResponseStatus(HttpStatus.OK)
  public Page<CommentResponse> getPostComments(
          @PathVariable Integer postId, @RequestParam Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return service.getPostComments(postId, pageable);
  }
}
