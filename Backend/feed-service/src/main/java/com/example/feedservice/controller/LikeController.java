package com.example.feedservice.controller;

import com.example.feedservice.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/posts/{postId}/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    /**
     * Add a like to a specific post.
     * @param postId ID of the post to like
     * @param userId ID of the user who likes the post
     * @return ResponseEntity with status OK
     */
    @PostMapping("/user/{userId}")
    public ResponseEntity<Void> addLike(@PathVariable Long postId, @PathVariable Long userId) {
        likeService.addLike(userId, postId);
        return ResponseEntity.ok().build();
    }

    /**
     * Remove a like from a specific post.
     * @param postId ID of the post to unlike
     * @param userId ID of the user who unlikes the post
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> removeLike(@PathVariable Long postId, @PathVariable Long userId) {
        likeService.removeLike(userId, postId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Count all likes for a specific post.
     * @param postId ID of the post
     * @return Number of likes for the post
     */
    @GetMapping("/count")
    public ResponseEntity<Long> countLikes(@PathVariable Long postId) {
        long count = likeService.countLikes(postId);
        return ResponseEntity.ok(count);
    }
}
