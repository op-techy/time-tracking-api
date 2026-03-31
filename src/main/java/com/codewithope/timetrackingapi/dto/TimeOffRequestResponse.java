package com.codewithope.timetrackingapi.dto;

import com.codewithope.timetrackingapi.entity.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class TimeOffRequestResponse {

    private UUID id;

    private UUID employeeId;

    private UUID approvedById;

    private String reason;

    private LocalDate startDate;

    private LocalDate endDate;

    private ApprovalStatus approvalStatus;

    private Instant createdAt;

}
