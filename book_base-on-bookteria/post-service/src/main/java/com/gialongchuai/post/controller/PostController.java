package com.gialongchuai.post.controller;

import com.gialongchuai.post.dto.request.PostRequest;
import com.gialongchuai.post.dto.response.ApiResponse;
import com.gialongchuai.post.dto.response.PageResponse;
import com.gialongchuai.post.dto.response.PostResponse;
import com.gialongchuai.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PostController {
    PostService postService;

    @PostMapping("/create")
    ApiResponse<PostResponse> create(@RequestBody PostRequest postRequest){
        return ApiResponse.<PostResponse>builder()
                .result(postService.create(postRequest))
                .build();
    }

    @PostMapping("")
    String testApi(@RequestBody PostRequest postRequest){
        return postRequest.getContent();
    }

    @GetMapping("/my-post")
    ApiResponse<PageResponse<PostResponse>> getMyPost(
            @RequestParam(value = "page", required = false, defaultValue = "1") int page,
            @RequestParam(value = "size", required = false, defaultValue = "5") int size
    ){
        return ApiResponse.<PageResponse<PostResponse>>builder()
                .result(postService.myPost(page, size))
                .build();
    }
}
