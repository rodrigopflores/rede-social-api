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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/privado/comment")
public class CommentPrivateController {

  @Autowired private CommentService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CommentResponse createComment(@Valid @RequestBody CreateCommentRequest request) {
    return service.createComment(request);
  }

  @GetMapping("/{postId}")
  @ResponseStatus(HttpStatus.OK)
  public Page<CommentResponse> getPostComments(
      @PathVariable Integer postId, @RequestParam Integer page, Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return service.getPostComments(postId, pageable);
  }
}
