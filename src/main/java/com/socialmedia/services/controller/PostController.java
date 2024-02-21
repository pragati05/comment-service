package com.socialmedia.services.controller;

import com.socialmedia.services.entity.Comment;
import com.socialmedia.services.mapper.PostMapper;
import com.socialmedia.services.models.PostRequest;
import com.socialmedia.services.models.PostResponse;
import com.socialmedia.services.service.PostService;
import com.socialmedia.services.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.socialmedia.services.entity.Post;

import java.util.List;


@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService;

    PostMapper postMapper = PostMapper.INSTANCE;

    @PostMapping
    public PostResponse addPost(@RequestBody PostRequest postRequest) {
        Post post = postMapper.postRequestToPost(postRequest);
        postService.addPost(post);
        return postMapper.postToPostResponse(post);
    }

    @GetMapping
    public List<PostResponse> getPosts() {
        List<Post> posts = postService.getPosts();
        return postMapper.postToPostResponses(posts);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        if (post != null)
            return ResponseEntity.ok(postMapper.postToPostResponse(post));
        else
            return ResponseEntity.notFound().build();
    }

    // Create a new comment for a post
    @PostMapping("/{postId}/comments")
    public Comment addComment(@PathVariable Long postId, @RequestBody Comment comment) {
        return commentService.addCommentForPost(postId, comment);
    }

}
