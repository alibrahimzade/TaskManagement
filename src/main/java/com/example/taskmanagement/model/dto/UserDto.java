package com.example.taskmanagement.model.dto;

import com.example.taskmanagement.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto {
    Long id;
    String username;
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    UserRole role;
}
