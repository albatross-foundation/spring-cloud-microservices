package com.instagram.news.domain;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class UserSummary {
    private String id;
    private String username;
    private String name;
    private String profilePicture;
}
