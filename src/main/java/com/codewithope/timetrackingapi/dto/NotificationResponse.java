package com.codewithope.timetrackingapi.dto;

import com.codewithope.timetrackingapi.entity.NotificationSeverity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class NotificationResponse {

    private UUID id;

    private UUID userId;

    private String message;

    private NotificationSeverity severity;

    private boolean isRead;

    private Instant createdAt;

  }