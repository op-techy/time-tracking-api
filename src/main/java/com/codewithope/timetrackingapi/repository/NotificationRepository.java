package com.codewithope.timetrackingapi.repository;

import com.codewithope.timetrackingapi.entity.Notification;
import com.codewithope.timetrackingapi.entity.Session;
import com.codewithope.timetrackingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {
    List<Notification> findByUserAndIsRead(User user, boolean isRead);
}