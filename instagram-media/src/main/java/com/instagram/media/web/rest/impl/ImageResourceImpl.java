package com.instagram.media.web.rest.impl;

import com.instagram.media.domain.ImageResponse;
import com.instagram.media.service.ImageService;
import com.instagram.media.web.rest.ImageResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Slf4j
@RestController
public class ImageResourceImpl implements ImageResource {

    private final ImageService imageService;

    public ImageResourceImpl(ImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    public ImageResponse uploadFile(MultipartFile file, Principal principal) {
        return imageService.upload(file, principal.getName());
    }
}
