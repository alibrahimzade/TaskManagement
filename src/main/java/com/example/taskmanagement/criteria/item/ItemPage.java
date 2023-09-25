package com.example.taskmanagement.criteria.item;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Sort;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemPage {
    Integer pageNumber;
    Integer pageSize;
    Sort.Direction sortDirection = Sort.Direction.ASC;
    String sortBy;
}
