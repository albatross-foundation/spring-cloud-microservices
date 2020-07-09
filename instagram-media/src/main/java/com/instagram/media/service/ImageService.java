package com.instagram.media.service;

import com.instagram.media.domain.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    public ImageResponse upload(MultipartFile file, String userName);
}
