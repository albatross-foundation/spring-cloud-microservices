package com.instagram.post.mapper;

import com.instagram.post.domain.PostRequest;
import com.instagram.post.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostRequestMapper extends EntityMapper<PostEntity, PostRequest> {
}
