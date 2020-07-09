package com.instagram.post.service.impl;

import com.instagram.post.domain.PostRequest;
import com.instagram.post.domain.PostResponse;
import com.instagram.post.entity.PostEntity;
import com.instagram.post.exception.NotAllowedException;
import com.instagram.post.exception.ResourceNotFoundException;
import com.instagram.post.mapper.PostEventMapper;
import com.instagram.post.mapper.PostMapper;
import com.instagram.post.mapper.PostRequestMapper;
import com.instagram.post.repository.PostRepository;
import com.instagram.post.service.PostEventService;
import com.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostMapper postMapper;

    private final PostEventMapper postEventMapper;

    private final PostRequestMapper postRequestMapper;

    private final PostEventService postEventService;

    public PostServiceImpl(PostRepository postRepository, PostMapper postMapper,
                           PostRequestMapper postRequestMapper, PostEventService postEventService,
                           PostEventMapper postEventMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.postRequestMapper = postRequestMapper;
        this.postEventService = postEventService;
        this.postEventMapper = postEventMapper;
    }

    @Override
    public PostResponse createPost(PostRequest request) {
        PostEntity postEntity = postRepository.save(postRequestMapper.toEntity(request));
        log.info("createPost: " + postEventMapper.toDomain(postEntity));
        postEventService.sendPostCreated(postEventMapper.toDomain(postEntity));
        return postMapper.toDomain(postEntity);
    }

    @Override
    public void deletePost(String postId, String userName) {
        postRepository.findById(postId)
                .map(postEntity -> {
                    if (!postEntity.getUsername().equals(userName)) {
                        throw new NotAllowedException(userName, "post id " + postId, "delete");
                    }
                    postRepository.delete(postEntity);
                    postEventService.sendPostDeleted(postEventMapper.toDomain(postEntity));
                    return postEntity;
                })
                .orElseThrow(() -> new ResourceNotFoundException(postId));
    }

    @Override
    public List<PostResponse> postsByUsername(String userName) {
        List<PostEntity> entities = postRepository.findByUsernameOrderByCreatedAtDesc(userName);
        return postMapper.toDomains(entities);
    }

    @Override
    public List<PostResponse> postsByIdsIn(List<String> ids) {
        List<PostEntity> entities = postRepository.findByIdInOrderByCreatedAtDesc(ids);
        return postMapper.toDomains(entities);
    }
}
