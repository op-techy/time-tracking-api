package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.entity.Session;
import com.codewithope.timetrackingapi.entity.User;
import com.codewithope.timetrackingapi.exception.ResourceNotFoundException;
import com.codewithope.timetrackingapi.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService{
    private final SessionRepository sessionRepository;

    @Override
    public Session clockIn(User user) {
        Session session = new Session();
        session.setUser(user);
        session.setClockIn(Instant.now());
        session.setActive(true);
        return sessionRepository.save(session);
    }

    @Override
    public void clockOut(UUID sessionId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(()-> new ResourceNotFoundException("Session not found"));
        session.setClockOut(Instant.now());
        session.setActive(false);
        sessionRepository.save(session);
    }

    @Override
    public List<Session> getSession(User user) {
        return sessionRepository.findByUser(user);
    }

    @Override
    public Optional<Session> getActiveSession(User user) {
        return sessionRepository.findByUserAndIsActive(user,true);
    }

    @Override
    public Optional<Session> findSessionById(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }
}
