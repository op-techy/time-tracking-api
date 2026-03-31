package com.codewithope.timetrackingapi.dto;

import com.codewithope.timetrackingapi.entity.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class TaskResponse {

    private UUID id;

    private String name;

    private TaskStatus status;

    private UUID sessionId;

    private Instant startTime;

    private Instant endTime;

    private Instant createdAt;

}
