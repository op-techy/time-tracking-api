package com.codewithope.timetrackingapi.controller;

import com.codewithope.timetrackingapi.dto.NotificationResponse;
import com.codewithope.timetrackingapi.entity.Notification;
import com.codewithope.timetrackingapi.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationsController {

    private final NotificationService notificationService;

    @GetMapping("/{id}")
    public ResponseEntity<List<NotificationResponse>> getNotifications(@PathVariable UUID id) {
        return ResponseEntity.ok(
                notificationService.getNotifications(id).stream()
                        .map(this::mapToResponse)
                        .toList()
        );
    }

   @GetMapping("/{id}/unread")
   public  ResponseEntity<List<NotificationResponse>> unreadNotifications(@PathVariable UUID id) {
       return  ResponseEntity.ok(
               notificationService.getUnreadNotification(id).stream()
               .map(this::mapToResponse)
               .toList()
       );
   }

   @PutMapping("/{id}/read")
   public ResponseEntity<Void> markNotificationAsRead(@PathVariable UUID id) {
       notificationService.markNotificationAsRead(id);
       return ResponseEntity.ok().build();
   }

    private NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .userId(notification.getUser().getId())
                .message(notification.getMessage())
                .severity(notification.getSeverity())
                .isRead(notification.isRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
