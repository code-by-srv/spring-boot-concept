package com.example.JWTAccessRefreshTokenAuth.services;






import com.example.JWTAccessRefreshTokenAuth.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
