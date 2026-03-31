package com.codewithope.timetrackingapi.controller;

import com.codewithope.timetrackingapi.dto.CreateTimeOffRequest;
import com.codewithope.timetrackingapi.dto.TimeOffRequestResponse;
import com.codewithope.timetrackingapi.entity.ApprovalStatus;
import com.codewithope.timetrackingapi.entity.TimeOffRequest;
import com.codewithope.timetrackingapi.service.TimeOffRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveTimeOffRequest(@PathVariable UUID id, @RequestBody UUID managerId) {
        timeOffRequestService.approveTimeOffRequest(id, managerId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reject")
    public ResponseEntity<Void> rejectTimeOffRequest(@PathVariable UUID id, @RequestBody UUID managerId) {
        timeOffRequestService.rejectTimeOffRequest(id, managerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{employeeId}/employee")
    public ResponseEntity<List<TimeOffRequestResponse>> getTimeOffRequestsByEmployee(@PathVariable UUID employeeId) {
        List<TimeOffRequest> timeOffRequests = timeOffRequestService.getTimeOffRequestByEmployee(employeeId);
        return ResponseEntity.ok(timeOffRequests.stream().map(this::mapToResponse).toList());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<TimeOffRequestResponse>> getTimeOffRequestsByStatus(@PathVariable ApprovalStatus status) {
        List<TimeOffRequest> timeOffRequests = timeOffRequestService.getTimeOffRequestByStatus(status);
        return ResponseEntity.ok(timeOffRequests.stream().map(this::mapToResponse).toList());
    }

    private TimeOffRequestResponse mapToResponse(TimeOffRequest timeOffRequest) {
        return TimeOffRequestResponse.builder()
                .id(timeOffRequest.getId())
                .reason(timeOffRequest.getReason())
                .startDate(timeOffRequest.getStartDate())
                .endDate(timeOffRequest.getEndDate())
                .approvalStatus(timeOffRequest.getApprovalStatus())
                .employeeId(timeOffRequest.getEmployee().getId())
                .approvedById(timeOffRequest.getApprovedBy() != null ? timeOffRequest.getApprovedBy().getId() : null)
                .build();
    }

}
