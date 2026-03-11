package com.codingsrv.SpringSecurityAuthentication.services;
import com.codingsrv.SpringSecurityAuthentication.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
