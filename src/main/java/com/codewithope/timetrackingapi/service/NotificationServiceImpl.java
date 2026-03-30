package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.entity.Notification;
import com.codewithope.timetrackingapi.entity.NotificationSeverity;
import com.codewithope.timetrackingapi.entity.User;
import com.codewithope.timetrackingapi.repository.NotificationRepository;
import com.codewithope.timetrackingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public Notification createNotification(UUID userId, String message, NotificationSeverity severity) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setMessage(message);
        notification.setSeverity(severity);
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> getNotifications(UUID userId) {
       return notificationRepository.findByUser_Id(userId);
    }

    @Override
    public List<Notification> getUnreadNotification(UUID userId) {
        return notificationRepository.findByUser_IdAndIsRead(userId,false);
    }

    @Override
    public void markNotificationAsRead(UUID notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        notification.setRead(true);
        notificationRepository.save(notification);
    }
}
