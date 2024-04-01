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
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("privado/post")
public class PostPrivateController {

    @Autowired
    private PostService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponse createPost(@Valid @RequestBody CreatePostRequest request) {
        return service.createPost(request);
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostResponse> getUserPosts(@PathVariable Integer userId, @RequestParam Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getUserPosts(userId, pageable);
    }

    @PostMapping("/{postId}/like")
    @ResponseStatus(HttpStatus.OK)
    public void likePost(@PathVariable Integer postId) {
        service.likePost(postId);
    }

    @GetMapping("feed")
    @ResponseStatus(HttpStatus.OK)
    public Page<PostResponse> getUserFeed(@RequestParam Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return service.getUserFeed(pageable);
    }
}
