package com.codewithope.timetrackingapi.controller;

import com.codewithope.timetrackingapi.dto.CreateTaskRequest;
import com.codewithope.timetrackingapi.dto.TaskResponse;
import com.codewithope.timetrackingapi.entity.Task;
import com.codewithope.timetrackingapi.entity.TaskStatus;
import com.codewithope.timetrackingapi.service.SessionService;
import com.codewithope.timetrackingapi.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final SessionService sessionService;
    private final TaskService taskService;

    @PostMapping("")
    public ResponseEntity<TaskResponse> createTask(@RequestBody CreateTaskRequest request) {
        Task task = taskService.createTask(request.getSessionId(), request.getTaskName());
        TaskResponse response = mapToResponse(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateTaskStatus(@PathVariable UUID id, @RequestBody TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/end")
    public ResponseEntity<Void> setEndTime(@PathVariable UUID id) {
        taskService.setEndTime(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<TaskResponse>> getTasks(@PathVariable UUID sessionId) {
        List<Task> tasks = taskService.getTasks(sessionId);
        return ResponseEntity.ok(tasks.stream().map(this::mapToResponse).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .status(task.getStatus())
                .sessionId(task.getSession().getId())
                .startTime(task.getStartTime())
                .endTime(task.getEndTime())
                .build();
    }
}
