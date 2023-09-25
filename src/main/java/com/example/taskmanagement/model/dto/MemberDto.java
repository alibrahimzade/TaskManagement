package com.example.taskmanagement.model.dto;

import jakarta.persistence.ElementCollection;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MemberDto {
    Long id;
    String name;

    private String imagesOfMember;
}
