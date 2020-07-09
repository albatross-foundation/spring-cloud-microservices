package com.instagram.post.mapper;

import com.instagram.post.domain.PostEvent;
import com.instagram.post.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostEventMapper extends EntityMapper<PostEntity, PostEvent> {
}
