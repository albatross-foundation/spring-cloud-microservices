package com.instagram.media.web.rest;

import com.instagram.media.domain.ImageResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Api(tags = "Image Resource")
//@RequestMapping("/media")
@Validated
public interface ImageResource {
    @ApiOperation(value = "Upload image")
    @PostMapping("/upload")
    @PreAuthorize("hasRole('USER')")
    ImageResponse uploadFile(
            @ApiParam(value = "Image") @RequestParam("image") MultipartFile file,
            @AuthenticationPrincipal Principal principal
    );
}
