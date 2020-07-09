package com.instagram.news.repository;

import com.instagram.news.entity.UserNewsFeed;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsFeedRepository extends CassandraRepository<UserNewsFeed, String> {
    Slice<UserNewsFeed> findByUsername(String username, Pageable pageable);
}
