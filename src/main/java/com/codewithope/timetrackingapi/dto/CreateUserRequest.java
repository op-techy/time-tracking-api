package com.codewithope.timetrackingapi.dto;

import com.codewithope.timetrackingapi.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateUserRequest {

    private String fullName;

    private String email;

    private String userName;

    private String password;

    private Role role;
}
