package com.instagram.post.web.rest;

import com.instagram.post.domain.PostRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Api(tags = "Post Resource")
//@RequestMapping("/post")
@Validated
public interface PostResource {
    @ApiOperation(value = "Create a post")
    @PostMapping("/create")
    ResponseEntity<?> createPost(
            @ApiParam(value = "Request body", required = true) @RequestBody PostRequest postRequest
    );

    @ApiOperation(value = "Delete a post by id")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePost(
            @ApiParam(value = "Post Id", required = true) @PathVariable("id") String id,
            @AuthenticationPrincipal Principal user
    );

    @ApiOperation(value = "Find posts of current user")
    @GetMapping("/me")
    ResponseEntity<?> findCurrentUserPosts(
            @AuthenticationPrincipal Principal principal
    );

    @ApiOperation(value = "Find posts by user name")
    @GetMapping("/{userName}")
    ResponseEntity<?> findUserPosts(
            @ApiParam(value = "User name", required = true) @PathVariable("userName") String userName
    );

    @ApiOperation(value = "Find posts by Ids")
    @PostMapping("/in")
    ResponseEntity<?> findPostsByIdIn(
            @ApiParam(value = "List of ids", required = true) @RequestBody List<String> ids
    );
}
