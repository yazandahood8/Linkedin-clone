package com.example.feedservice.utils;

import com.example.feedservice.dto.PostRequestDTO;
import com.example.feedservice.dto.PostResponseDTO;
import com.example.feedservice.model.Post;

public class PostMapper {

    public static Post toEntity(PostRequestDTO dto) {
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setVisibility(dto.getVisibility());
        post.setUserId(dto.getUserId());
        return post;
    }

    public static PostResponseDTO toDTO(Post post) {
        PostResponseDTO dto = new PostResponseDTO();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setVisibility(post.getVisibility());
        dto.setUserId(post.getUserId());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        return dto;
    }
}
