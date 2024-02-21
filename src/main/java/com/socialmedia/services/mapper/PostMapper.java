package com.socialmedia.services.mapper;

import com.socialmedia.services.entity.Post;
import com.socialmedia.services.models.PostResponse;
import com.socialmedia.services.models.PostRequest;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface PostMapper {

    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(source = "user.id", target = "user_id")
    PostResponse postToPostResponse(Post post);

    @Mapping(source = "user_id", target = "user.id")
    Post postRequestToPost(PostRequest postRequest);

    List<PostResponse> postToPostResponses(List<Post> posts);
}