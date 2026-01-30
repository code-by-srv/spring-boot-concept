package com.codingsrv.SpringBootAuditing.controller;

import com.codingsrv.SpringBootAuditing.dto.PostDTO;
import com.codingsrv.SpringBootAuditing.service.PostService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PutMapping("/{postId}")
    public PostDTO updatePostById(@RequestBody PostDTO inputPost, @PathVariable Long postId){
        return postService.updatePostById(inputPost, postId);
    }


}
