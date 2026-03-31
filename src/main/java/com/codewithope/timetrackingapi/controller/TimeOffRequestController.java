package com.codewithope.timetrackingapi.controller;

import com.codewithope.timetrackingapi.dto.CreateTimeOffRequest;
import com.codewithope.timetrackingapi.dto.TimeOffRequestResponse;
import com.codewithope.timetrackingapi.entity.TimeOffRequest;
import com.codewithope.timetrackingapi.service.TimeOffRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/time-off-requests")
@RequiredArgsConstructor
public class TimeOffRequestController {

    private final TimeOffRequestService timeOffRequestService;

    @PostMapping("/create")
    public ResponseEntity<TimeOffRequestResponse> createTimeOffRequest(@RequestBody CreateTimeOffRequest request){
        TimeOffRequest timeOffRequest = timeOffRequestService.submitTimeOffRequest(request);
        TimeOffRequestResponse response = mapToResponse(timeOffRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

//    @PutMapping("/{id}/approve")
//    public ResponseEntity<Void> approveTimeOffRequest(@PathVariable UUID id) {
//
//    }

//    @PutMapping("/{id}/reject")
//    public ResponseEntity<Void> rejectTimeOffRequest(@PathVariable UUID id) {
//
//    }

    private TimeOffRequestResponse mapToResponse(TimeOffRequest timeOffRequest) {
        return TimeOffRequestResponse.builder()
                .id(timeOffRequest.getId())
                .reason(timeOffRequest.getReason())
                .startDate(timeOffRequest.getStartDate())
                .endDate(timeOffRequest.getEndDate())
                .approvalStatus(timeOffRequest.getApprovalStatus())
                .employeeId(timeOffRequest.getEmployee().getId())
                .approvedById(timeOffRequest.getApprovedBy().getId())
                .build();
    }

}
