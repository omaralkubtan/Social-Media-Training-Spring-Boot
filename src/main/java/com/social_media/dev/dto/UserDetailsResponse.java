package com.social_media.dev.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDetailsResponse {

    private Long id;

    private String username;

    private List<RoleListResponse> roles;

    @JsonProperty("permissions")
    private List<PermissionResponse> authorities;

}
