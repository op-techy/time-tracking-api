package com.codewithope.timetrackingapi.repository;

import com.codewithope.timetrackingapi.entity.Session;
import com.codewithope.timetrackingapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    List<Session> findByUser(User user);
    Optional<Session> findByUserAndIsActive(User user, boolean isActive);
}