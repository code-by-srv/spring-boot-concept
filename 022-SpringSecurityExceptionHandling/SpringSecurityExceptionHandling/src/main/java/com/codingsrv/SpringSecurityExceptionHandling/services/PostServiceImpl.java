package com.codingsrv.SpringSecurityExceptionHandling.services;

import com.codingsrv.SpringSecurityExceptionHandling.dto.PostDTO;
import com.codingsrv.SpringSecurityExceptionHandling.entities.PostEntity;
import com.codingsrv.SpringSecurityExceptionHandling.entities.User;
import com.codingsrv.SpringSecurityExceptionHandling.exception.ResourceNotFoundException;
import com.codingsrv.SpringSecurityExceptionHandling.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
       return modelMapper.map(postRepository.save(postEntity), PostDTO.class);

    }

    @Override
    public PostDTO getPostById(Long postId) {
       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       log.info("user {}",user);

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found by this id "+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }


}
