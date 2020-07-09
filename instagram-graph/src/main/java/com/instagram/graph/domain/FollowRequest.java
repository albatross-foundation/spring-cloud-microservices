package com.instagram.graph.domain;

import lombok.Data;

@Data
public class FollowRequest {
    UserResponse follower;
    UserResponse following;
}
