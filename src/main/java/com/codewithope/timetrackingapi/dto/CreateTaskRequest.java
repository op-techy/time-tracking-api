package com.codewithope.timetrackingapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateTaskRequest {
    private UUID sessionId;
    private String taskName;
}
