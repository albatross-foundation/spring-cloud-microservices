package com.instagram.graph.domain;

import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String username;
    private String name;
    private String profilePicture;
}
