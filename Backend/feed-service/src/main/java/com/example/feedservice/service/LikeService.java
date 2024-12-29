package com.example.feedservice.service;

import com.example.feedservice.exception.UserNotAllowedException;
import com.example.feedservice.model.Like;
import com.example.feedservice.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;

    public LikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void addLike(Long userId, Long postId) {
        if (likeRepository.findByUserIdAndPostId(userId, postId).isPresent()) {
            throw new UserNotAllowedException("User already liked this post.");
        }

        Like like = new Like();
        like.setUserId(userId);
        like.setPostId(postId);
        likeRepository.save(like);
    }

    public void removeLike(Long userId, Long postId) {
        Like like = likeRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new UserNotAllowedException("Like not found for user and post."));
        likeRepository.delete(like);
    }

    public long countLikes(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}
