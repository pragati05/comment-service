package com.socialmedia.services.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.socialmedia.services.entity.Post;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Add a method to find all posts by a user
    List<Post> findByUserId(Long userId);

}
