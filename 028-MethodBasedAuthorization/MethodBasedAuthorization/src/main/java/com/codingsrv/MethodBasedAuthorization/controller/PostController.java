package com.codingsrv.MethodBasedAuthorization.controller;

import com.codingsrv.MethodBasedAuthorization.dto.PostDTO;
import com.codingsrv.MethodBasedAuthorization.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;


    @PostMapping
    public PostDTO createNewPost(@RequestBody PostDTO inputPost){
        return postService.createNewPost(inputPost);
    }

    @GetMapping("/{postId}")
//    @PreAuthorize("hasAnyRole('ADMIN','CREATOR') AND hasAuthority('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPost(#postId)")
    public PostDTO getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @GetMapping
    @Secured({"ROLE_ADMIN","ROLE_ADMIN"})
    public List<PostDTO> getAllPosts(){
        return postService.getAllPosts();
    }

}
