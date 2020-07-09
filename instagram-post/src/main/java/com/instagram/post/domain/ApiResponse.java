package com.instagram.post.domain;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;
}
