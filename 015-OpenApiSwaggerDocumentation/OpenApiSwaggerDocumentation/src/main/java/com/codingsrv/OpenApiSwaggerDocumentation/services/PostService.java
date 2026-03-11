package com.codingsrv.OpenApiSwaggerDocumentation.services;

import com.codingsrv.OpenApiSwaggerDocumentation.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
