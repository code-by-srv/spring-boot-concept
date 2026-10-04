package com.codingsrv.MethodBasedAuthorization.services;


import com.codingsrv.MethodBasedAuthorization.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
