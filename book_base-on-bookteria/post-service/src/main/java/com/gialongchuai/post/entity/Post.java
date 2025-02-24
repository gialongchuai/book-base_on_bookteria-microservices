package com.gialongchuai.post.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.Instant;

@Setter
@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(value = "post")
public class Post {
    @MongoId
    String id;
    String userId;
    String content;
    Instant createDate;
    Instant modifiedDate;
}
