package com.instagram.graph.service.impl;

import com.instagram.graph.domain.PagedResult;
import com.instagram.graph.entity.Friendship;
import com.instagram.graph.entity.NodeDegree;
import com.instagram.graph.entity.User;
import com.instagram.graph.exception.UsernameAlreadyExistsException;
import com.instagram.graph.exception.UsernameNotExistsException;
import com.instagram.graph.repository.UserRepository;
import com.instagram.graph.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            String message = String.format("username %s already exists", user.getUsername());
            log.warn(message);
            throw new UsernameAlreadyExistsException(message);
        }
        User saveUser = userRepository.save(user);
        log.info("user {} save successfully", saveUser.getUsername());
        return saveUser;
    }

    @Override
    public User updateUser(User user) {
        return userRepository
                .findByUsername(user.getUsername())
                .map(savedUser -> {
                    savedUser.setName(user.getName());
                    savedUser.setUsername(user.getUsername());
                    savedUser.setProfilePic(user.getProfilePic());

                    savedUser = userRepository.save(savedUser);
                    log.info("user {} updated successfully", savedUser.getUsername());

                    return savedUser;
                })
                .orElseThrow(() -> new UsernameNotExistsException(
                        String.format("user %s not exists", user.getUsername())));
    }

    @Transactional
    @Override
    public User follow(User follower, User following) {
        log.info("user {} will follow {}",
                follower.getUsername(), following.getUsername());

        User savedFollower = userRepository
                .findByUserId(follower.getUserId())
                .orElseGet(() -> {
                    log.info("user {} not exists, creating it", follower.getUsername());
                    return this.addUser(follower);
                });

        User savedFollowing = userRepository
                .findByUserId(following.getUserId())
                .orElseGet(() -> {
                    log.info("user {} not exits, creating it", following.getUsername());
                    return this.addUser(following);
                });

        if (savedFollower.getFriendships() == null) {
            savedFollower.setFriendships(new HashSet<>());
        }

        savedFollower
                .getFriendships()
                .add(Friendship.builder()
                        .startNode(savedFollower)
                        .endNode(savedFollowing)
                        .build());

        return userRepository.save(savedFollower);
    }

    @Override
    public NodeDegree findNodeDegree(String username) {
        log.info("fetching degree for user {}", username);
        long out = userRepository.findOutDegree(username);
        long in = userRepository.findInDegree(username);
        log.info("found {} outdegree and {} indegree for user {}", out, in, username);
        return NodeDegree
                .builder()
                .outDegree(out)
                .inDegree(in)
                .build();
    }

    @Override
    public boolean isFollowing(String userA, String userb) {
        return userRepository.isFollowing(userA, userb);
    }

    @Override
    public List<User> findFollowers(String username) {
        List<User> followers = userRepository.findFollowers(username);
        log.info("found {} followers for user {}", followers.size(), username);

        return followers;
    }

    @Override
    public PagedResult<User> findPaginatedFollowers(String userName, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> followers = userRepository.findFollowers(userName, pageable);
        log.info("found {} followers for user {}", followers.getTotalElements(), userName);

        return buildPagedResult(followers);
    }

    @Override
    public List<User> findFollowing(String username) {
        List<User> following = userRepository.findFollowing(username);
        log.info("found {} that user {} is following", following.size(), username);
        return following;
    }

    private PagedResult<User> buildPagedResult(Page<User> page) {
        return PagedResult
                .<User>builder()
                .content(page.getContent())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .page(page.getPageable().getPageNumber())
                .size(page.getSize())
                .last(page.isLast())
                .build();
    }
}
