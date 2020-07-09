package com.instagram.post.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface PostEventStream {
    String OUTPUT = "instagramPostChanged";

    @Output(OUTPUT)
    MessageChannel instagramPostChanged();
}
