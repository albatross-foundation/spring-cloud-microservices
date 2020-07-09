package com.instagram.media.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class ImageEntity {
    @Id
    private String id;
    @CreatedBy
    private String username;
    @CreatedDate
    private Instant createdAt;
    @NonNull
    private String fileName;
    @NonNull
    private String uri;
    @NonNull
    private String fileType;
}
