package com.socialmedia.services.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Setter
public class CommentRequest {
    private String text;
    private Long userId;
    private Long parentCommentId;
}