package com.instagram.graph.web.rest;

import com.instagram.graph.domain.FollowRequest;
import com.instagram.graph.util.AppConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Graph Resource")
//@RequestMapping("/graph")
@Validated
public interface UserResource {

    @ApiOperation(value = "Request follow")
    @PostMapping("/followers")
    ResponseEntity<?> follow(
            @ApiParam(value = "Request body", required = true) @RequestBody FollowRequest request
    );

    @ApiOperation(value = "Find node degree")
    @GetMapping("/{username}/degree")
    ResponseEntity<?> findNodeDegree(
            @ApiParam(value = "Username", required = true) @PathVariable String username
    );

    @ApiOperation(value = "Check is following")
    @GetMapping("/{usernameA}/following/{usernameB}")
    ResponseEntity<?> isFollwoing(
            @ApiParam(value = "Username A", required = true) @PathVariable String usernameA,
            @ApiParam(value = "Username B", required = true) @PathVariable String usernameB
    );

    @ApiOperation(value = "Find follower")
    @GetMapping("/{username}/followers")
    ResponseEntity<?> findFollowers(
            @ApiParam(value = "Username", required = true) @PathVariable String username
    );

    @ApiOperation(value = "Find followers paginated")
    @GetMapping("/paginated/{username}/followers")
    ResponseEntity<?> findFollowersPaginated(
            @ApiParam(value = "Username", required = true) @PathVariable String username,
            @ApiParam(value = "Page", required = true) @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @ApiParam(value = "Size", required = true) @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size
    );

    @ApiOperation(value = "Find following")
    @GetMapping("/{username}/following")
    ResponseEntity<?> findFollowing(
            @ApiParam(value = "Username", required = true) @PathVariable String username
    );
}
