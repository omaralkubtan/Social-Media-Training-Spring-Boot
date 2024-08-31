package com.social_media.dev.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserLightWithoutPermissionResponse {

    @NotNull
    private Long id;

    @NotNull
    private String username;
}
