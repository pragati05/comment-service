package com.socialmedia.services.repository;

import com.socialmedia.services.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByUserIdAndCommentIdAndDislike(Long userId, Long commentId, boolean dislike);
}
