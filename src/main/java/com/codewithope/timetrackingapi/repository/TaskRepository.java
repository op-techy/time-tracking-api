package com.codewithope.timetrackingapi.repository;

import com.codewithope.timetrackingapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findBySession_Id(UUID sessionId);
}