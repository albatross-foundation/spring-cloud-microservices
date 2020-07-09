package com.instagram.post.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Builder
@ToString
@Document
@NoArgsConstructor
@AllArgsConstructor
public class PostEntity {
    @Id
    private String id;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    @CreatedBy
    private String username;

    @LastModifiedBy
    private String lastModifiedBy;

    @NonNull
    private String imageUrl;

    @NonNull
    private String caption;
}
