package com.codewithope.timetrackingapi.service;

import com.codewithope.timetrackingapi.entity.Role;
import com.codewithope.timetrackingapi.entity.User;
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
    public User createUser(User user) {
        user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserRole(UUID id, Role role) {
      User user = userRepository.findById(id)
              .orElseThrow(() -> new RuntimeException("User not found"));
      user.setRole(role);
      userRepository.save(user);

    }

    @Override
    public void updateUserPassword(UUID id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
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
