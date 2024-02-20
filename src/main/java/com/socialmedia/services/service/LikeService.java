package com.socialmedia.services.service;

import com.socialmedia.services.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmedia.services.entity.Like;



@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;

    public void addLike(Like like) {
        likeRepository.save(like);
    }

    public Like getLikeByUserIdAndCommentIdAndDislike(Long userId, Long commentId, boolean dislike) {
        return likeRepository.findByUserIdAndCommentIdAndDislike(userId, commentId, dislike);
    }

    public void deleteLike(Like like) {
        likeRepository.delete(like);
    }
}
