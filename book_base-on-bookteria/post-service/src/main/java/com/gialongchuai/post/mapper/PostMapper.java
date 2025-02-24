package com.gialongchuai.post.mapper;

import com.gialongchuai.post.dto.response.PostResponse;
import com.gialongchuai.post.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
