package com.example.feedservice.service;

import com.example.feedservice.dto.CommentRequestDTO;
import com.example.feedservice.dto.CommentResponseDTO;
import com.example.feedservice.exception.CommentNotFoundException;
import com.example.feedservice.model.Comment;
import com.example.feedservice.repository.CommentRepository;
import com.example.feedservice.utils.CommentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public CommentResponseDTO addComment(CommentRequestDTO request) {
        Comment comment = CommentMapper.toEntity(request);
        comment = commentRepository.save(comment);
        return CommentMapper.toDTO(comment);
    }

    public List<CommentResponseDTO> getCommentsForPost(Long postId) {
        return commentRepository.findByPostId(postId).stream()
                .map(CommentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteComment(Long id) {
        if (!commentRepository.existsById(id)) {
            throw new CommentNotFoundException("Comment with id " + id + " not found.");
        }
        commentRepository.deleteById(id);
    }
}
