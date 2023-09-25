package com.example.taskmanagement.model.dto;

import com.example.taskmanagement.dao.entity.Member;
import com.example.taskmanagement.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {
    Long id;

    String name;

    String description;

    LocalDateTime createdAt;
    Long memberId;
    @Enumerated(EnumType.STRING)
    Status status;
}
