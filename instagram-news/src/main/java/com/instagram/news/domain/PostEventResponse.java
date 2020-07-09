package com.instagram.news.domain;

import com.instagram.news.message.PostEventType;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class PostEventResponse {
    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private String username;
    private String lastModifiedBy;
    private String imageUrl;
    private String caption;
    private PostEventType eventType;
}
