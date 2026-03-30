package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.entity.Task;
import com.codewithope.timetrackingapi.entity.TaskStatus;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    Task createTask(UUID sessionId, String taskName);
    void updateTaskStatus(UUID taskId, TaskStatus taskStatus);
    void setEndTime(UUID taskId);
    List<Task> getTasks(UUID sessionId);
    void deleteTask(UUID taskId);
}
