package com.example.feedservice.controller;

import com.example.feedservice.dto.CommentRequestDTO;
import com.example.feedservice.dto.CommentResponseDTO;
import com.example.feedservice.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Add a comment to a specific post.
     * @param postId ID of the post
     * @param request CommentRequestDTO containing the comment details
     * @return CommentResponseDTO with the created comment details
     */
    @PostMapping
    public ResponseEntity<CommentResponseDTO> addComment(
            @PathVariable Long postId,
            @RequestBody CommentRequestDTO request) {
        request.setPostId(postId); // Ensure the Post ID is included in the request
        CommentResponseDTO response = commentService.addComment(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Get all comments for a specific post.
     * @param postId ID of the post
     * @return List of CommentResponseDTO containing all comments for the post
     */
    @GetMapping
    public ResponseEntity<List<CommentResponseDTO>> getCommentsForPost(@PathVariable Long postId) {
        List<CommentResponseDTO> comments = commentService.getCommentsForPost(postId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Delete a comment by its ID.
     * @param postId ID of the post (not used in this method but part of the URL structure)
     * @param commentId ID of the comment to delete
     * @return No content response
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
