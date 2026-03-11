package com.codingsrv.SpringSecurityFilterChain.services;


import com.codingsrv.SpringSecurityFilterChain.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
