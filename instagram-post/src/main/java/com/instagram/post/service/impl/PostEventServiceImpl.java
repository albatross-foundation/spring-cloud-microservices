package com.instagram.post.service.impl;

import com.instagram.post.domain.PostEvent;
import com.instagram.post.message.PostEventStream;
import com.instagram.post.message.PostEventType;
import com.instagram.post.service.PostEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PostEventServiceImpl implements PostEventService {

    private final PostEventStream channels;

    public PostEventServiceImpl(PostEventStream channels) {
        this.channels = channels;
    }

    @Override
    public void sendPostCreated(PostEvent postEvent) {
        postEvent.setEventType(PostEventType.CREATED);
        sendPostChangedEvent(postEvent);
    }

    @Override
    public void sendPostUpdated(PostEvent postEvent) {
        postEvent.setEventType(PostEventType.UPDATED);
        sendPostChangedEvent(postEvent);
    }

    @Override
    public void sendPostDeleted(PostEvent postEvent) {
        postEvent.setEventType(PostEventType.DELETED);
        sendPostChangedEvent(postEvent);
    }

    private void sendPostChangedEvent(PostEvent postEvent) {
        log.info("Send post successfully: " + postEvent.getEventType());
        Message<PostEvent> message = MessageBuilder
                .withPayload(postEvent)
                .setHeader(KafkaHeaders.MESSAGE_KEY, postEvent.getId())
                .build();
        channels.instagramPostChanged().send(message);
    }
}
