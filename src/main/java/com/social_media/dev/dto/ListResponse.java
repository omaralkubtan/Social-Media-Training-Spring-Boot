package com.social_media.dev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.social_media.dev.util.helper.Constants;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ListResponse {
    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime createdAt;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime updatedAt;

    @JsonFormat(pattern = Constants.DATE_TIME_FORMAT)
    private LocalDateTime deletedAt;
}
