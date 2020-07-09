package com.instagram.news.message;

import com.instagram.news.domain.Post;
import com.instagram.news.domain.PostEventResponse;
import com.instagram.news.service.NewsFeedGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PostEventListener {

    private final NewsFeedGeneratorService newsFeedGeneratorService;

    public PostEventListener(NewsFeedGeneratorService newsFeedGeneratorService) {
        this.newsFeedGeneratorService = newsFeedGeneratorService;
    }

    @StreamListener(PostEventStream.INPUT)
    public void onMessage(Message<PostEventResponse> message) {

        PostEventType eventType = message.getPayload().getEventType();

        log.info("received message to process post {} for user {} eventType {}",
                message.getPayload().getId(),
                message.getPayload().getUsername(),
                eventType.name());

        Acknowledgment acknowledgment =
                message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);


        switch (eventType) {
            case CREATED:
                newsFeedGeneratorService.addToFeed(convertTo(message.getPayload()));
                break;
            case DELETED:
                break;
        }

        if (acknowledgment != null) {
            acknowledgment.acknowledge();
        }
    }

    private Post convertTo(PostEventResponse payload) {
        return Post
                .builder()
                .id(payload.getId())
                .createdAt(payload.getCreatedAt())
                .username(payload.getUsername())
                .build();
    }
}
