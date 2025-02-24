package com.gialongchuai.post.service;

import com.gialongchuai.post.dto.request.PostRequest;
import com.gialongchuai.post.dto.response.ApiResponse;
import com.gialongchuai.post.dto.response.PostResponse;
import com.gialongchuai.post.entity.Post;
import com.gialongchuai.post.mapper.PostMapper;
import com.gialongchuai.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;

    public PostResponse create(PostRequest postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var userId = authentication.getName();

        Post post = Post.builder()
                .userId(userId)
                .content(postRequest.getContent())
                .createDate(Instant.now())
                .modifiedDate(Instant.now())
                .build();

        return postMapper.toPostResponse(postRepository.save(post));
    }

    public List<PostResponse> getMyPost() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var userId = authentication.getName();

        var posts = postRepository.findAllByUserId(userId);

        return posts.stream().map(postMapper::toPostResponse).toList();
    }
}
