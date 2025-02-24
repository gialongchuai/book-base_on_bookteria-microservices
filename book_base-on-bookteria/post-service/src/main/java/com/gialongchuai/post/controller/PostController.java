package com.gialongchuai.post.controller;

import com.gialongchuai.post.dto.request.PostRequest;
import com.gialongchuai.post.dto.response.ApiResponse;
import com.gialongchuai.post.dto.response.PostResponse;
import com.gialongchuai.post.service.PostService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/get-my-post")
    ApiResponse<List<PostResponse>> getMyPost(){
        return ApiResponse.<List<PostResponse>>builder()
                .result(postService.getMyPost())
                .build();
    }
}
