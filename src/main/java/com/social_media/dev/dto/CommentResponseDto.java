package com.social_media.dev.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {
    private Long id;
    private String content;
    private UserLightWithoutPermissionResponse createdBy;
    private LocalDateTime createdAt;
}