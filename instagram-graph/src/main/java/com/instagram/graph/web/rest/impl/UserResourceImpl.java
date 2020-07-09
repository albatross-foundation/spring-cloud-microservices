package com.instagram.graph.web.rest.impl;

import com.instagram.graph.domain.ApiResponse;
import com.instagram.graph.domain.FollowRequest;
import com.instagram.graph.entity.User;
import com.instagram.graph.service.UserService;
import com.instagram.graph.web.rest.UserResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserResourceImpl implements UserResource {

    private final UserService userService;

    public UserResourceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<?> follow(FollowRequest request) {
        log.info("received a follow request follow {} following {}",
                request.getFollower().getUsername(),
                request.getFollowing().getUsername());


        userService.follow(
                User.builder()
                        .userId(request.getFollower().getId())
                        .username(request.getFollower().getUsername())
                        .name(request.getFollower().getName())
                        .profilePic(request.getFollower().getProfilePicture())
                        .build(),

                User.builder()
                        .userId(request.getFollowing().getId())
                        .username(request.getFollowing().getUsername())
                        .name(request.getFollowing().getName())
                        .profilePic(request.getFollowing().getProfilePicture())
                        .build()
        );

        String message = String.format("user %s is following user %s",
                request.getFollower().getUsername(),
                request.getFollowing().getUsername());

        log.info(message);

        return ResponseEntity.ok(new ApiResponse(true, message));
    }

    @Override
    public ResponseEntity<?> findNodeDegree(String username) {
        log.info("received request to get node degree for {}", username);

        return ResponseEntity.ok(userService.findNodeDegree(username));
    }

    @Override
    public ResponseEntity<?> isFollwoing(String usernameA, String usernameB) {
        log.info("received request to check is user {} is following {}"
                , usernameA, usernameB);

        return ResponseEntity.ok(userService.isFollowing(usernameA, usernameB));
    }

    @Override
    public ResponseEntity<?> findFollowers(String username) {
        return ResponseEntity.ok(userService.findFollowers(username));
    }

    @Override
    public ResponseEntity<?> findFollowersPaginated(String username, int page, int size) {
        return ResponseEntity.ok(userService.findPaginatedFollowers(username, page, size));
    }

    @Override
    public ResponseEntity<?> findFollowing(String username) {
        return ResponseEntity.ok(userService.findFollowing(username));
    }
}
