package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.entity.Session;
import com.codewithope.timetrackingapi.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SessionService {
    Session clockIn(User user);
    void clockOut(UUID sessionId);
    List<Session> getSession(User user);
    Optional<Session> getActiveSession(User user);
}
