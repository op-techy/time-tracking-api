package com.codewithope.timetrackingapi.controller;

import com.codewithope.timetrackingapi.dto.CreateUserRequest;
import com.codewithope.timetrackingapi.dto.UserResponse;
import com.codewithope.timetrackingapi.entity.Role;
import com.codewithope.timetrackingapi.entity.User;
import com.codewithope.timetrackingapi.exception.ResourceNotFoundException;
import com.codewithope.timetrackingapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping ("/createuser")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createUser(request);
        UserResponse response = mapToResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAll() {
        return ResponseEntity.ok(
                userService.getAll().stream()
                        .map(this::mapToResponse)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable UUID id) {
        User user = userService.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User not found"));
        UserResponse response = mapToResponse(user);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/role")
    public ResponseEntity<Void> updateUserRole(@PathVariable UUID id, @RequestBody Role role) {
        userService.updateUserRole(id,role);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> updateUserPassword(@PathVariable UUID id, @RequestBody String newPassword) {
        userService.updateUserPassword(id,newPassword);
        return ResponseEntity.ok().build();
    }



    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .userName(user.getUserName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .isActive(user.isActive())
                .build();
    }

}
