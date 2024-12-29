package com.example.feedservice.service;

import com.example.feedservice.dto.PostRequestDTO;
import com.example.feedservice.dto.PostResponseDTO;
import com.example.feedservice.exception.PostNotFoundException;
import com.example.feedservice.model.Post;
import com.example.feedservice.repository.PostRepository;
import com.example.feedservice.utils.PostMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /**
     * Create a new post.
     */
    public PostResponseDTO createPost(PostRequestDTO request) {
        Post post = PostMapper.toEntity(request);
        post = postRepository.save(post);
        return PostMapper.toDTO(post);
    }

    /**
     * Get all posts.
     */
    public List<PostResponseDTO> getAllPosts() {
        return postRepository.findAll().stream()
                .map(PostMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a specific post by ID.
     */
    public PostResponseDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with id " + id + " not found."));
        return PostMapper.toDTO(post);
    }

    /**
     * Update an existing post by ID.
     */
    public PostResponseDTO updatePost(Long id, PostRequestDTO request) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with id " + id + " not found."));
        
        // Update fields
        post.setContent(request.getContent());
        post.setVisibility(request.getVisibility());
        post.setUserId(request.getUserId());
        
        // Save updated post
        post = postRepository.save(post);
        return PostMapper.toDTO(post);
    }

    /**
     * Delete a post by ID.
     */
    public void deletePost(Long id) {
        if (!postRepository.existsById(id)) {
            throw new PostNotFoundException("Post with id " + id + " not found.");
        }
        postRepository.deleteById(id);
    }
}
