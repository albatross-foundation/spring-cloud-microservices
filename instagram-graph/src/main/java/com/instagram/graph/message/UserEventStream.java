package com.instagram.graph.message;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface UserEventStream {

    String INPUT = "instagramGraphChanged";

    @Input(INPUT)
    SubscribableChannel instagramGraphChanged();
}
