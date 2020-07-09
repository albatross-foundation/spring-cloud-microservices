package com.instagram.media.service.impl;

import com.instagram.media.domain.ImageResponse;
import com.instagram.media.entity.ImageEntity;
import com.instagram.media.mapper.ImageResponseMapper;
import com.instagram.media.repository.ImageRepository;
import com.instagram.media.service.FileStorageService;
import com.instagram.media.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final FileStorageService fileStorageService;

    private final ImageResponseMapper mapper;

    public ImageServiceImpl(ImageRepository imageRepository, FileStorageService fileStorageService, ImageResponseMapper mapper) {
        this.imageRepository = imageRepository;
        this.fileStorageService = fileStorageService;
        this.mapper = mapper;
    }

    @Override
    public ImageResponse upload(MultipartFile file, String userName) {
        ImageResponse imageResponse = fileStorageService.store(file, userName);
        ImageEntity imageEntity = imageRepository.save(mapper.toEntity(imageResponse));
        return mapper.toDomain(imageEntity);
    }
}
