package com.social_media.dev.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class RoleListResponse extends ListResponse {
    @NotNull
    private Long id;

    @NotNull
    private String name;
}
