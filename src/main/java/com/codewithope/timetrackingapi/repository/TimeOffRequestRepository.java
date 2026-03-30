package com.codewithope.timetrackingapi.repository;

import com.codewithope.timetrackingapi.entity.ApprovalStatus;
import com.codewithope.timetrackingapi.entity.TimeOffRequest;
import com.codewithope.timetrackingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TimeOffRequestRepository extends JpaRepository<TimeOffRequest, UUID> {
    List<TimeOffRequest> findByApprovalStatus(ApprovalStatus approvalStatus);
    List<TimeOffRequest> findByEmployee(User employee);
}