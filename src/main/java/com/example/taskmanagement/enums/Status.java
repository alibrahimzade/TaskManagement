package com.example.taskmanagement.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum Status {
    TO_DO("To Do"),
    In_Progress("In Progress"),
    In_Review("In Review"),
    Done("Done");

    private final String message;
}
