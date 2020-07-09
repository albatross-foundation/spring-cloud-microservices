package com.instagram.news.web.rest;

import com.instagram.news.domain.Post;
import com.instagram.news.domain.SlicedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Api(tags = "NewsFeed Resource")
//@RequestMapping("/news")
@Validated
public interface NewsFeedResource {

    @ApiOperation(value = "Get news feed")
    @GetMapping("/{username}")
    ResponseEntity<SlicedResult<Post>> getFeed(
            @ApiParam(value = "Username", required = true) @PathVariable String username,
            @ApiParam(value = "Paging state", required = true) @RequestParam(value = "ps", required = false) Optional<String> pagingState
    );
}
