package com.instagram.post.domain;

import lombok.*;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
    private String imageUrl;
    private String caption;
}
