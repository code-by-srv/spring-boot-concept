package com.codingsrv.MethodBasedAuthorization.utils;

import com.codingsrv.MethodBasedAuthorization.dto.PostDTO;
import com.codingsrv.MethodBasedAuthorization.entities.User;
import com.codingsrv.MethodBasedAuthorization.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostSecurity {

    private final PostService postService;

    public boolean isOwnerOfPost(Long postId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostDTO post = postService.getPostById(postId);
        return post.getAuthor().getId().equals(user.getId());
    }
}
