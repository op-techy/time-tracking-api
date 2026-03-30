package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.entity.Notification;
import com.codewithope.timetrackingapi.entity.NotificationSeverity;
import com.codewithope.timetrackingapi.entity.User;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    Notification createNotification(UUID userId, String message, NotificationSeverity severity);
    List<Notification> getNotifications(UUID userid);
    List<Notification> getUnreadNotification(UUID userid);
    void markNotificationAsRead(UUID notificationId);
}
