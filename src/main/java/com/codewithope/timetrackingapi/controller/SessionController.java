package com.codewithope.timetrackingapi.controller;

import com.codewithope.timetrackingapi.dto.SessionResponse;
import com.codewithope.timetrackingapi.entity.Session;
import com.codewithope.timetrackingapi.entity.User;
import com.codewithope.timetrackingapi.exception.ResourceNotFoundException;
import com.codewithope.timetrackingapi.service.SessionService;
import com.codewithope.timetrackingapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/session")
@RequiredArgsConstructor
public class SessionController {

    private final UserService userService;
    private final SessionService sessionService;

    @PostMapping("/clockin")
    public ResponseEntity<SessionResponse> clockIn(@RequestBody UUID userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Session session = sessionService.clockIn(user);
        SessionResponse response = mapToReponse(session);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}/clockout")
    public ResponseEntity<Void> clockOut(@PathVariable UUID id) {
        sessionService.clockOut(id);
        return  ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SessionResponse>> getSession(@PathVariable UUID userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        return ResponseEntity.ok(
                sessionService.getSession(user).stream()
                        .map(this::mapToReponse)
                        .toList()
        );
    }

    @GetMapping("/{id}/active")
    public ResponseEntity<SessionResponse> getActiveSession(@PathVariable UUID id) {
        User user = userService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return  sessionService.getActiveSession(user)
                .map(session -> ResponseEntity.ok(mapToReponse(session)))
                .orElse(ResponseEntity.notFound().build());
    }

    private SessionResponse mapToReponse(Session session){
        return SessionResponse.builder()
                .id(session.getId())
                .userId(session.getUser().getId())
                .clockIn(session.getClockIn())
                .clockOut(session.getClockOut())
                .isActive(session.isActive())
                .createdAt(session.getCreatedAt())
                .build();
    }

}
