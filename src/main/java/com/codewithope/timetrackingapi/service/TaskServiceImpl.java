package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.entity.Session;
import com.codewithope.timetrackingapi.entity.Task;
import com.codewithope.timetrackingapi.entity.TaskStatus;
import com.codewithope.timetrackingapi.exception.ResourceNotFoundException;
import com.codewithope.timetrackingapi.repository.SessionRepository;
import com.codewithope.timetrackingapi.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService{
    private final SessionRepository sessionRepository;
    private final TaskRepository taskRepository;

    @Override
    public Task createTask(UUID sessionId, String taskName) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new ResourceNotFoundException("Session not found"));
        Task task = new Task();
        task.setName(taskName);
        task.setSession(session);
        task.setStatus(TaskStatus.NOT_STARTED);
        return taskRepository.save(task);
    }

    @Override
    public void updateTaskStatus(UUID taskId, TaskStatus taskStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setStatus(taskStatus);
        taskRepository.save(task);
    }

    @Override
    public void setEndTime(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        task.setEndTime(Instant.now());
        task.setStatus(TaskStatus.COMPLETED);
        taskRepository.save(task);
    }

    @Override
    public List<Task> getTasks(UUID sessionId) {

        return taskRepository.findBySession_Id(sessionId);    }

    @Override
    public void deleteTask(UUID taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found"));
        taskRepository.delete(task);
    }
}
