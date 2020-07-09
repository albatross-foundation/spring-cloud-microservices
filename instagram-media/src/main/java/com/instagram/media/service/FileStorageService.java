package com.instagram.media.service;

import com.instagram.media.domain.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    public ImageResponse store(MultipartFile file, String userName);
}
