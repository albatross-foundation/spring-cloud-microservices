package com.instagram.news.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.MessageChannel;

public interface PostEventStream {
    String INPUT = "instagramPostChanged";

    @Input(INPUT)
    MessageChannel instagramPostChanged();
}
