package com.socialmedia.services.mapper;

import com.socialmedia.services.entity.Comment;
import com.socialmedia.services.models.CommentRequestForPost;
import com.socialmedia.services.models.CommentRequestForReply;
import com.socialmedia.services.models.CommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {


    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    // Mappings for Comments on a Post

    @Mapping(source = "userId", target = "user.id")
    Comment commentRequestForPostToComment(CommentRequestForPost commentRequest);


    // Mappings for Reply on Comments

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "postId", target = "post.id")
    Comment commentRequestForReplyToComment(CommentRequestForReply commentRequest);


    // Common Mappings

    @Mapping(source = "post.id", target = "postId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "parentComment.id", target = "parentCommentId")
    CommentResponse commentToCommentResponse(Comment comment);

    List<CommentResponse> commentsToCommentResponses(List<Comment> comments);


}
