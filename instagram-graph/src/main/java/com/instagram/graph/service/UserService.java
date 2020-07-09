package com.instagram.graph.service;

import com.instagram.graph.domain.PagedResult;
import com.instagram.graph.entity.NodeDegree;
import com.instagram.graph.entity.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    User updateUser(User user);
    User follow(User follower, User following);
    NodeDegree findNodeDegree(String username);
    boolean isFollowing(String userA, String userb);
    List<User> findFollowers(String username);
    PagedResult<User> findPaginatedFollowers(String username, int page, int size);
    List<User> findFollowing(String username);
}
