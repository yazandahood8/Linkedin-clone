package com.example.feedservice.utils;

import com.example.feedservice.dto.CommentRequestDTO;
import com.example.feedservice.dto.CommentResponseDTO;
import com.example.feedservice.model.Comment;

public class CommentMapper {

    public static Comment toEntity(CommentRequestDTO dto) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setUserId(dto.getUserId());
        comment.setPostId(dto.getPostId());
        return comment;
    }

    public static CommentResponseDTO toDTO(Comment comment) {
        CommentResponseDTO dto = new CommentResponseDTO();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setUserId(comment.getUserId());
        dto.setPostId(comment.getPostId());
        dto.setCreatedAt(comment.getCreatedAt());
        dto.setUpdatedAt(comment.getUpdatedAt());
        return dto;
    }
}
