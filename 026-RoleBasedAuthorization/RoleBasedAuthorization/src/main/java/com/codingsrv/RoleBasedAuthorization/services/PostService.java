package com.codingsrv.RoleBasedAuthorization.services;




import com.codingsrv.RoleBasedAuthorization.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
