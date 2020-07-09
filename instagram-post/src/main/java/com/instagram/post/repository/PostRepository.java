package com.instagram.post.repository;

import com.instagram.post.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {
    List<PostEntity> findByUsernameOrderByCreatedAtDesc(String username);
    List<PostEntity> findByIdInOrderByCreatedAtDesc(List<String> ids);
}
