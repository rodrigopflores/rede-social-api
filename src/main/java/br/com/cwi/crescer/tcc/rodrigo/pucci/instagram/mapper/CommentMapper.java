package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    private static ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private UserMapper userMapper;

    public Comment toDomain(CreateCommentRequest request, User commenter, Post post) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setCommenter(commenter);
        comment.setPost(post);

        return comment;
    }

    public CommentResponse toCommentResponse(Comment comment) {
        CommentResponse response = new CommentResponse();
        UserStandardResponse commenter = userMapper.toUserStandardResponse(comment.getCommenter());
        response.setCommenter(commenter);
        response.setId(comment.getId());
        response.setPostId(comment.getPost().getId());
        response.setTime(comment.getTime());
        response.setContent(comment.getContent());

        return response;

    }
}
