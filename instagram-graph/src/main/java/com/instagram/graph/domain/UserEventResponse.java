package com.instagram.graph.domain;

import com.instagram.graph.message.UserEventType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserEventResponse {
    private String id;
    private String username;
    private String email;
    private String displayName;
    private String profilePictureUrl;
    private String oldProfilePicUrl;
    private UserEventType eventType;
}
