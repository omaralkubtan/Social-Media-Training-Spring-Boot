package com.social_media.dev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.social_media.dev.util.helper.Constants;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DetailsResponse {
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createdAt;

    private UserLightWithoutPermissionResponse createdBy;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime updatedAt;

    private UserLightWithoutPermissionResponse updatedBy;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime deletedAt;

    private UserLightWithoutPermissionResponse deletedBy;
}
