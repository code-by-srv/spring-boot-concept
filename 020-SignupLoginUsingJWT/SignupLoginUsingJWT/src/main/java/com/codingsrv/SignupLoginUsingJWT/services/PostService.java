package com.codingsrv.SignupLoginUsingJWT.services;




import com.codingsrv.SignupLoginUsingJWT.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
