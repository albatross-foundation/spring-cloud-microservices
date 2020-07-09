package com.instagram.post.service;

import com.instagram.post.domain.PostEvent;

public interface PostEventService {
    void sendPostCreated(PostEvent postEvent);
    void sendPostUpdated(PostEvent postEvent);
    void sendPostDeleted(PostEvent postEvent);
}
