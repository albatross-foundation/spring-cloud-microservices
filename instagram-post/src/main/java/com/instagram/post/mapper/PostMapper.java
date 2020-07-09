package com.instagram.post.mapper;

import com.instagram.post.domain.PostResponse;
import com.instagram.post.entity.PostEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends EntityMapper<PostEntity, PostResponse> {
}
