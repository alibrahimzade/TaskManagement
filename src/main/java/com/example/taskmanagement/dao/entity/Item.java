package com.example.taskmanagement.dao.entity;

import com.example.taskmanagement.enums.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "items")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    @JsonIgnore
    Member member;
    @Enumerated(EnumType.STRING)
    Status status;
}
