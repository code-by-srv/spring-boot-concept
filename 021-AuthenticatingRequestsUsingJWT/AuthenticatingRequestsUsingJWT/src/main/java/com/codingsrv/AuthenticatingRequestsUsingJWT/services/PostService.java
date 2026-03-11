package com.codingsrv.AuthenticatingRequestsUsingJWT.services;



import com.codingsrv.AuthenticatingRequestsUsingJWT.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
