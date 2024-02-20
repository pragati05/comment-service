package com.socialmedia.services.controller;

import com.socialmedia.services.entity.Comment;
import com.socialmedia.services.entity.Like;
import com.socialmedia.services.entity.User;
import com.socialmedia.services.service.CommentService;
import com.socialmedia.services.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.socialmedia.services.service.LikeService;


@RestController
@RequestMapping("/comments")
public class CommentController {
    @Value("${reply_depth}")
    private int replyDepth;

    @Autowired
    private CommentService commentService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private UserService userService;


    @GetMapping("/{commentId}")
    public Comment getComment(@PathVariable Long commentId) {
        return commentService.getCommentById(commentId);
    }

    @PutMapping("/{commentId}/like")
    public Comment likeComment(@PathVariable Long commentId, @RequestParam Long userId) {
        return likeHelper(commentId, userId, false);
    }

    @PutMapping("/{commentId}/dislike")
    public Comment dislikeComment(@PathVariable Long commentId, @RequestParam Long userId) {
        return likeHelper(commentId, userId, true);
    }

    public Comment likeHelper(Long commentId, Long userId, boolean dislike) {
        Comment comment = commentService.getCommentById(commentId);
        User user = userService.getUserById(userId);

        Like existingLike = likeService.getLikeByUserIdAndCommentIdAndDislike(userId, commentId, dislike);

        if(existingLike != null && existingLike.isDislike() == dislike){
            return comment;
        }else{
            if(existingLike != null){
                if(existingLike.isDislike()){
                    comment.setDislikes(comment.getDislikes() - 1);
                }else{
                    comment.setLikes(comment.getLikes() - 1);
                }
                likeService.deleteLike(existingLike);
            }
        }

        Like like = new Like();

        like.setComment(comment);
        like.setUser(user);

        if(dislike){
            like.setDislike(true);
            comment.setDislikes(comment.getDislikes() + 1);
        } else {
            comment.setLikes(comment.getLikes() + 1);
        }

        // entry made in like table
        likeService.addLike(like);

        // save the updated comment
        commentService.addOrUpdateComment(comment);

        return comment;
    }


    @PostMapping("/{parentCommentId}/reply")
    public ResponseEntity<?> replyToComment(@PathVariable Long parentCommentId, @RequestBody Comment reply) {
        Comment comment = commentService.getCommentById(parentCommentId);

        if(comment.getDepth() > replyDepth){
            return ResponseEntity.badRequest().body("Reply depth cannot be more than " + replyDepth);
        }

        reply.setParentComment(comment);
        reply.setDepth(comment.getDepth() + 1);

        commentService.addOrUpdateComment(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(reply);
    }


}
