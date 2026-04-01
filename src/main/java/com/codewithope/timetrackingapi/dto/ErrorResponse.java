package com.codewithope.timetrackingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Builder
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private Instant timestamp;
    private int statusCode;
}
