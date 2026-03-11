package com.codingsrv.JWTCreationVerification.services;


import com.codingsrv.JWTCreationVerification.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
