package com.codewithope.timetrackingapi.dto;

import com.codewithope.timetrackingapi.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Builder
@Getter
@AllArgsConstructor
public class UserResponse {

    private UUID id;

    private String fullName;

    private String email;

    private String userName;

    private Role role;

    private Instant createdAt;

    private Instant updatedAt;

    private boolean isActive;
}
