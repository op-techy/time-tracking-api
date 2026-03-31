package com.codewithope.timetrackingapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateTimeOffRequest {

    private String reason;

    private LocalDate startDate;

    private LocalDate endDate;

    private UUID employeeId;


}
