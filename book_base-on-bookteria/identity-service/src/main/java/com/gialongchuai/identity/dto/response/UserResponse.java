package com.gialongchuai.identity.dto.response;

import java.time.LocalDate;
import java.util.Set;

import com.gialongchuai.identity.entity.Role;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String firstName;
    String lastName;
    String username;
    LocalDate dob;
    Set<Role> roles;
}
