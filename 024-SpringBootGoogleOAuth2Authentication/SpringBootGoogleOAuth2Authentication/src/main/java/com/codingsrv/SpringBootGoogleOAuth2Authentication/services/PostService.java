package com.codingsrv.SpringBootGoogleOAuth2Authentication.services;


import com.codingsrv.SpringBootGoogleOAuth2Authentication.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
