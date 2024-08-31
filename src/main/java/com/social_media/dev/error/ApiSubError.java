package com.social_media.dev.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ApiSubError {
    private final String name;
    private final String message;
}
