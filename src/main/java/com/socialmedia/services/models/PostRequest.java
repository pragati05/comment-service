package com.socialmedia.services.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PostRequest {
    private String title;
    private String content;
    private Long user_id;
}