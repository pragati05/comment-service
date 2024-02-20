package com.socialmedia.services.service;

import com.socialmedia.services.entity.Comment;
import com.socialmedia.services.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.socialmedia.services.entity.Post;



import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostService postService;

    public Comment getCommentById(Long commentId) {
        return commentRepository.findById(commentId).orElse(null);
    }

    public List<Comment> getComments(Long parentId) {
        return commentRepository.findByPostId(parentId);
    }

    public Comment addOrUpdateComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment addCommentForPost(Long postId, Comment comment) {
        Post post = postService.getPostById(postId);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Comment updateLikes(Long commentId, Long likes) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            comment.setLikes(likes);
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment updateDislikes(Long commentId, Long dislikes) {
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment != null) {
            comment.setDislikes(dislikes);
            return commentRepository.save(comment);
        }
        return null;
    }
}