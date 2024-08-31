package com.social_media.dev.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {
    private Long id;
    private String content;
    private UserLightWithoutPermissionResponse createdBy;
    private LocalDateTime createdAt;
    private int likeCount;
}