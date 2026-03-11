package com.codingsrv.SpringSecurityExceptionHandling.services;




import com.codingsrv.SpringSecurityExceptionHandling.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
