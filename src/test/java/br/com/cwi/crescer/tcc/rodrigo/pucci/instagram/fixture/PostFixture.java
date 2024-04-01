package br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.fixture;

import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.Post;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.domain.User;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.request.CreatePostRequest;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.PostResponse;
import br.com.cwi.crescer.tcc.rodrigo.pucci.instagram.representation.response.UserStandardResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostFixture {

    public static Post post() {
        User user = UserFixture.user();
        Post post = new Post();
        post.setId(2);
        post.setUser(user);
        post.setImage("https://randomwordgenerator.com/img/picture-generator/57e4dd46495aa814f1dc8460962e33791c3ad6e04e50744172297cd59444c3_640.jpg");
        post.setMessage("Novo post");
        post.setTime(LocalDateTime.of(2021, 10, 17, 10, 45, 31));
        post.setPrivatePost(false);
        post.setLikes(new ArrayList<>());

        return post;
    }

    public static CreatePostRequest createPostRequest() {
        CreatePostRequest request = new CreatePostRequest();
        request.setImage("https://randomwordgenerator.com/img/picture-generator/57e4dd46495aa814f1dc8460962e33791c3ad6e04e50744172297cd59444c3_640.jpg");
        request.setMessage("Novo post");
        request.setPrivatePost(false);

        return request;
    }

    public static PostResponse postResponse() {
        UserStandardResponse user = UserFixture.userStandardResponse();

        PostResponse response = new PostResponse();
        response.setUser(user);
        response.setId(2);
        response.setLikes(5);
        response.setTime(LocalDateTime.of(2021, 10, 17, 10, 45, 31));
        response.setImage("https://randomwordgenerator.com/img/picture-generator/57e4dd46495aa814f1dc8460962e33791c3ad6e04e50744172297cd59444c3_640.jpg");
        response.setMessage("Novo post");
        response.setPrivatePost(false);
        response.setUserLiked(false);

        return response;
    }
}
