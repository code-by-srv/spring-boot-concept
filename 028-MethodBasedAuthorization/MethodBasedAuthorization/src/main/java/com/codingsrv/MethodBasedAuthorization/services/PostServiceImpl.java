package com.codingsrv.MethodBasedAuthorization.services;

import com.codingsrv.MethodBasedAuthorization.dto.PostDTO;
import com.codingsrv.MethodBasedAuthorization.entities.PostEntity;
import com.codingsrv.MethodBasedAuthorization.entities.User;
import com.codingsrv.MethodBasedAuthorization.exception.ResourceNotFoundException;
import com.codingsrv.MethodBasedAuthorization.repositories.PostRepository;
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        postEntity.setAuthor(user);
       return modelMapper.map(postRepository.save(postEntity), PostDTO.class);

    }

    @Override
    public PostDTO getPostById(Long postId) {
//       User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  // Not needed as user is not present in Authorization
//       log.info("user {}",user);

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post not found by this id "+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }




}
