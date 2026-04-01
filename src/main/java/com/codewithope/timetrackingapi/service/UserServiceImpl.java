package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.dto.CreateUserRequest;
import com.codewithope.timetrackingapi.entity.Role;
import com.codewithope.timetrackingapi.entity.User;
import com.codewithope.timetrackingapi.exception.ResourceNotFoundException;
import com.codewithope.timetrackingapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User createUser(CreateUserRequest request) {
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setUserName(request.getUserName());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setActive(true);
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserRole(UUID id, Role role) {
      User user = userRepository.findById(id)
              .orElseThrow(() -> new ResourceNotFoundException("User not found"));
      user.setRole(role);
      userRepository.save(user);

    }

    @Override
    public void updateUserPassword(UUID id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        user.setPasswordHash(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
