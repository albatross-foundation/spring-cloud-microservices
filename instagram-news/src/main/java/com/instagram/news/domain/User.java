package com.instagram.news.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private String userId;
    private String username;
}
