package com.codewithope.timetrackingapi.service;


import com.codewithope.timetrackingapi.dto.CreateTimeOffRequest;
import com.codewithope.timetrackingapi.entity.ApprovalStatus;
import com.codewithope.timetrackingapi.entity.TimeOffRequest;

import java.util.List;
import java.util.UUID;

public interface TimeOffRequestService {
    TimeOffRequest submitTimeOffRequest(CreateTimeOffRequest request);
    void approveTimeOffRequest(UUID requestId, UUID managerId);
    void rejectTimeOffRequest(UUID requestId, UUID managerId);
    List<TimeOffRequest> getTimeOffRequestByEmployee(UUID employeeId);
    List<TimeOffRequest> getTimeOffRequestByStatus(ApprovalStatus approvalStatus);
}
