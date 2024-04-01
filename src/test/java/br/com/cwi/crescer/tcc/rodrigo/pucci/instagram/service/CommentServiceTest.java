package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.service;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Comment;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.CommentFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.PostFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture.UserFixture;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.mapper.CommentMapper;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.repository.CommentRepository;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreateCommentRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.CommentResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {

    @InjectMocks
    private CommentService service;

    @Mock
    private CommentRepository repository;

    @Mock
    private CommentMapper mapper;

    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    @Test
    public void deveCriarCommentQuandoInformadoCreateCommentRequest() {

        // Arrange

        User commenter = UserFixture.user();
        Post post = PostFixture.post();
        CreateCommentRequest request = CommentFixture.createCommentRequest();
        Comment comment = CommentFixture.comment();
        CommentResponse response = CommentFixture.commentResponse();

        // Act

        when(userService.getUser())
                .thenReturn(commenter);
        when(postService.getValidatedPostById(request.getPostId()))
                .thenReturn(post);
        when(mapper.toDomain(request, commenter, post))
                .thenReturn(comment);
        when(repository.save(comment))
                .thenReturn(comment);
        when(mapper.toCommentResponse(comment))
                .thenReturn(response);

        CommentResponse result = service.createComment(request);

        verify(userService).getUser();
        verify(postService).getValidatedPostById(request.getPostId());
        verify(mapper).toDomain(request, commenter, post);
        verify(repository).save(comment);
        verify(mapper).toCommentResponse(comment);

        // Assert

        assertEquals(response.getId(), result.getId());

    }

    @Test
    public void deveRestornarPageDeCommentResponseQuandoInformarPostIdEPageable() {

        // Arrange

        Integer postId = 2;
        Post post = PostFixture.post();
        Pageable pageable = PageRequest.of(0, 5);
        Comment comment = CommentFixture.comment();
        Page<Comment> commentPage = new PageImpl<>(Collections.singletonList(comment), pageable, 1);
        CommentResponse response = CommentFixture.commentResponse();
        response.setId(comment.getId());

        // Act

        when(postService.getValidatedPostById(postId))
                .thenReturn(post);
        when(repository.findByPostIdOrderByTimeAsc(postId, pageable))
                .thenReturn(commentPage);
        when(mapper.toCommentResponse(comment))
                .thenReturn(response);

        Page<CommentResponse> result = service.getPostComments(postId, pageable);

        verify(postService).getValidatedPostById(postId);
        verify(repository).findByPostIdOrderByTimeAsc(postId, pageable);
        verify(mapper).toCommentResponse(comment);

        // Assert

        assertEquals(comment.getId(), result.get().collect(Collectors.toList()).get(0).getId());

    }
}