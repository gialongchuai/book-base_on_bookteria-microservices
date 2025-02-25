package com.gialongchuai.post.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class PostResponse {
    String id;
    String userId;
    String username;
    String content;
    String created;
    Instant createDate;
    Instant modifiedDate;
}
