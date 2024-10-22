package com.fullstack.movies.backend.exceptions.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public enum AuthenticationErrorCode implements ErrorCode {
    FAILED("AUTH_001","Authentication failed");


    private final String code;
    private final String description;
}