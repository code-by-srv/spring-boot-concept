package com.codingsrv.GranularAuthorization.services;

import com.codingsrv.GranularAuthorization.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
