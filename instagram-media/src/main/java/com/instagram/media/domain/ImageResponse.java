package com.instagram.media.domain;

import lombok.*;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponse {
    private String fileName;
    private String uri;
    private String fileType;
}
