package com.codingsrv.LoggingUsingSLF4J.services;




import com.codingsrv.LoggingUsingSLF4J.dto.PostDTO;

import java.util.List;

public interface PostService {

    public List<PostDTO> getAllPosts();

    public PostDTO createNewPost(PostDTO inputPost);


    PostDTO getPostById(Long postId);
}
