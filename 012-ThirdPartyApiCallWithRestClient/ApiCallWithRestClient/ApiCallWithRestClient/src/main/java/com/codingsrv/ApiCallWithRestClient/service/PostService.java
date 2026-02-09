package com.codingsrv.ApiCallWithRestClient.service;

import com.codingsrv.ApiCallWithRestClient.dto.PostDTO;
import com.codingsrv.ApiCallWithRestClient.entity.PostEntity;
import com.codingsrv.ApiCallWithRestClient.exception.ResourceNotFoundException;
import com.codingsrv.ApiCallWithRestClient.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;


    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .toList();
    }


    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);

    }


    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found by this id "+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }


}

