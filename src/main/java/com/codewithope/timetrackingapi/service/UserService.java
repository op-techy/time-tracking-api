package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.dto.CreateUserRequest;
import com.codewithope.timetrackingapi.entity.Role;
import com.codewithope.timetrackingapi.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    User createUser(CreateUserRequest request);
    Optional<User> findById(UUID id);
    void deleteUser(UUID id);
    void updateUserRole(UUID id, Role role);
    void updateUserPassword(UUID id, String newPassword);
    Optional<User> findByUsername(String userName);
    List<User> getAll();

}
