package com.codingsrv.prod_ready_features.prod_ready_features.services;


import com.codingsrv.prod_ready_features.prod_ready_features.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
