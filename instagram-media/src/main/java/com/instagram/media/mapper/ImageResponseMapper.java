package com.instagram.media.mapper;

import com.instagram.media.domain.ImageResponse;
import com.instagram.media.entity.ImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageResponseMapper extends EntityMapper<ImageEntity, ImageResponse> {
}
