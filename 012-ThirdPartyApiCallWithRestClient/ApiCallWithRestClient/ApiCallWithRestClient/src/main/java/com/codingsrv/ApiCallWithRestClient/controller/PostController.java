package com.codingsrv.ApiCallWithRestClient.controller;

import com.codingsrv.ApiCallWithRestClient.dto.PostDTO;
import com.codingsrv.ApiCallWithRestClient.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost) {
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }


}

