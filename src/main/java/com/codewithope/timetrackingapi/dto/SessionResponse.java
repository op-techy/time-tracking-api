package com.codewithope.timetrackingapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class SessionResponse {

    private UUID id;

    private UUID userId;

    private Instant clockIn;

    private Instant clockOut;

    private boolean isActive;

    private Instant createdAt;

}
