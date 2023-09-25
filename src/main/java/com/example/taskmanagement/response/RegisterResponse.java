package com.example.taskmanagement.response;

import com.example.taskmanagement.dao.entity.UserEntity;
import com.example.taskmanagement.enums.UserRole;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterResponse {
    String username;
    String email;
    String password;
    UserRole role;

    public static RegisterResponse buildRegisterDto(UserEntity userEntity) {
        return RegisterResponse.builder()
                .username(userEntity.getUsername())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .build();
    }
}
