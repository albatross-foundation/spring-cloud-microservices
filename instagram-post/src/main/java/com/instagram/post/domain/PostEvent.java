package com.instagram.post.domain;

import com.instagram.post.message.PostEventType;
import lombok.*;

import java.time.Instant;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostEvent {
    private String id;
    private Instant createdAt;
    private Instant updatedAt;
    private String username;
    private String lastModifiedBy;
    private String imageUrl;
    private String caption;
    private PostEventType eventType;
}
