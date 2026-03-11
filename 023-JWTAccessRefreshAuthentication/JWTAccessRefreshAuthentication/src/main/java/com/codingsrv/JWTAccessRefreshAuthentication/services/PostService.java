package com.codingsrv.JWTAccessRefreshAuthentication.services;



import com.codingsrv.JWTAccessRefreshAuthentication.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
