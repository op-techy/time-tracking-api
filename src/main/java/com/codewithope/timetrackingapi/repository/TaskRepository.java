package com.codewithope.timetrackingapi.repository;

import com.codewithope.timetrackingapi.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {
}