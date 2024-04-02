package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.CommentMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.CommentRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

  @Autowired private CommentRepository repository;

  @Autowired private CommentMapper mapper;

  @Autowired private UserService userService;

  @Autowired private PostService postService;

  public CommentResponse createComment(CreateCommentRequest request) {
    User commenter = userService.getUser();
    Post post = postService.getValidatedPostById(request.getPostId());

    Comment comment = mapper.toDomain(request, commenter, post);
    comment.setTime(LocalDateTime.now());
    comment = repository.save(comment);

    return mapper.toCommentResponse(comment);
  }

  public Page<CommentResponse> getPostComments(Integer postId, Pageable pageable) {
    Post post = postService.getValidatedPostById(postId);

    Page<Comment> commentPage = repository.findByPostIdOrderByTimeAsc(postId, pageable);
    List<CommentResponse> commentResponseList =
        commentPage
            .get()
            .map(comment -> mapper.toCommentResponse(comment))
            .collect(Collectors.toList());

    return new PageImpl<>(commentResponseList, pageable, commentPage.getTotalElements());
  }
}
