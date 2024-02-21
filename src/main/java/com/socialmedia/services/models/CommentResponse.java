package com.socialmedia.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Setter
public class CommentResponse {
    private Long id;
    private LocalDateTime createdAt;
    private String text;
    private Long postId;
    private Long userId;
    private Long parentCommentId;
    private Integer depth;
    private Long likes;
    private Long dislikes;
}