package com.codingsrv.SpringBootActuator.services;

import com.codingsrv.SpringBootActuator.dto.PostDTO;
import com.codingsrv.SpringBootActuator.entities.PostEntity;
import com.codingsrv.SpringBootActuator.exception.ResourceNotFoundException;
import com.codingsrv.SpringBootActuator.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    private static final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public List<PostDTO> getAllPosts() {

        log.info("Fetching all posts");

        List<PostEntity> posts = postRepository.findAll();

        log.debug("Total posts fetched: {}", posts.size());

        log.trace("Posts fetched from DB: {}", posts);

        return posts.stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .toList();
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {

        log.info("Creating new post with title: {}", inputPost.getTitle());

        log.debug("Input PostDTO received: {}", inputPost);

        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);

        PostEntity savedPost = postRepository.save(postEntity);

        log.info("Post created successfully with id: {}", savedPost.getId());

        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public PostDTO getPostById(Long postId) {

        log.info("Fetching post with id: {}", postId);

        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> {
                    log.error("Post not found with id: {}", postId);
                    return new ResourceNotFoundException("Post not found with id " + postId);
                });

        log.debug("Post found: {}", postEntity);

        return modelMapper.map(postEntity, PostDTO.class);
    }
}