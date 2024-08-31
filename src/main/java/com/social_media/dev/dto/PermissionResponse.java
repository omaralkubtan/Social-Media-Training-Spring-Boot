package com.social_media.dev.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class PermissionResponse {
    @NotNull
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private String name;
}
