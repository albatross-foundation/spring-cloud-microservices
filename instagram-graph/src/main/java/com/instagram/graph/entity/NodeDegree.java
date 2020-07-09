package com.instagram.graph.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NodeDegree {
    long outDegree;
    long inDegree;
}
