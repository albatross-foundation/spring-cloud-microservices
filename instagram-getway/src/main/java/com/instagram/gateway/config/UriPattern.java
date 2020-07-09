package com.instagram.gateway.config;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpMethod;

@Data
@Builder
@EqualsAndHashCode
public class UriPattern {
    private HttpMethod method;
    private String uriPattern;
}
