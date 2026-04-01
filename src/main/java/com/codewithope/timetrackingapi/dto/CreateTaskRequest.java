package com.codewithope.timetrackingapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateTaskRequest {

    @NotNull
    private UUID sessionId;

    @NotBlank
    private String taskName;
}
