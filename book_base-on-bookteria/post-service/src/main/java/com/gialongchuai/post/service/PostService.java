package com.gialongchuai.post.service;

import com.gialongchuai.post.dto.request.PostRequest;
import com.gialongchuai.post.dto.response.PageResponse;
import com.gialongchuai.post.dto.response.PostResponse;
import com.gialongchuai.post.entity.Post;
import com.gialongchuai.post.mapper.PostMapper;
import com.gialongchuai.post.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;
    DateTimeFormatter dateTimeFormatter;

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

    public PageResponse<PostResponse> myPost(int page, int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var userId = authentication.getName();

        Sort sort = Sort.by("createDate").descending();

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = postRepository.findAllByUserId(userId,pageable);

        var listDate = pageData.getContent().stream().map(post -> {
            var postResponse = postMapper.toPostResponse(post);
            postResponse.setCreated(dateTimeFormatter.format(post.getCreateDate())); // duyet qua tung post de format time create thich hop
            return postResponse;
        }).toList();

        return PageResponse.<PostResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getSize())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(listDate)
                .build();
    }
}
