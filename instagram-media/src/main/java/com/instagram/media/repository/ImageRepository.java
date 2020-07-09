package com.instagram.media.repository;

import com.instagram.media.entity.ImageEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends MongoRepository<ImageEntity, String> {
}
