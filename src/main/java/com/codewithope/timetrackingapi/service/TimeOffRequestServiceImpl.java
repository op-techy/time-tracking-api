package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.dto.CreateTimeOffRequest;
import com.codewithope.timetrackingapi.entity.ApprovalStatus;
import com.codewithope.timetrackingapi.entity.TimeOffRequest;
import com.codewithope.timetrackingapi.entity.User;
import com.codewithope.timetrackingapi.repository.TimeOffRequestRepository;
import com.codewithope.timetrackingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TimeOffRequestServiceImpl implements TimeOffRequestService{

    private final TimeOffRequestRepository timeOffRequestRepository;
    private final UserRepository userRepository;

    @Override
    public TimeOffRequest submitTimeOffRequest(CreateTimeOffRequest request) {
        User employee = userRepository.findById(request.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        TimeOffRequest timeOffRequest = new TimeOffRequest();
        timeOffRequest.setReason(request.getReason());
        timeOffRequest.setStartDate(request.getStartDate());
        timeOffRequest.setEndDate(request.getEndDate());
        timeOffRequest.setEmployee(employee);
        timeOffRequest.setApprovalStatus(ApprovalStatus.PENDING);

        return timeOffRequestRepository.save(timeOffRequest);
    }

    @Override
    public void approveTimeOffRequest(UUID requestId, UUID managerId) {
        TimeOffRequest timeOffRequest = timeOffRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Time off request not found"));
        timeOffRequest.setApprovalStatus(ApprovalStatus.APPROVED);
        User approvedBy = userRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        timeOffRequest.setApprovedBy(approvedBy);
        timeOffRequestRepository.save(timeOffRequest);
    }

    @Override
    public void rejectTimeOffRequest(UUID requestId, UUID managerId) {
        TimeOffRequest timeOffRequest = timeOffRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Time off request not found"));
        timeOffRequest.setApprovalStatus(ApprovalStatus.REJECTED);
        User approvedBy = userRepository.findById(managerId)
                .orElseThrow(() -> new RuntimeException("Manager not found"));
        timeOffRequest.setApprovedBy(approvedBy);
        timeOffRequestRepository.save(timeOffRequest);
    }

    @Override
    public List<TimeOffRequest> getTimeOffRequestByEmployee(UUID employeeId) {
        return timeOffRequestRepository.getTimeOffRequestsByEmployee_Id(employeeId);
    }

    @Override
    public List<TimeOffRequest> getTimeOffRequestByStatus(ApprovalStatus approvalStatus) {
        return timeOffRequestRepository.findByApprovalStatus(approvalStatus);
    }
}
