package com.example.taskmanagement.criteria.item;

import com.example.taskmanagement.dao.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemCriteriaController {

    private final ItemCriteriaService criteriaService;

    @GetMapping("/criteria")
    public ResponseEntity<Page<Item>> getItemWithCriteria(ItemPage itemPage,ItemSearchCriteria searchCriteria){
        return ResponseEntity.ok(criteriaService.getItemWithCriteria(itemPage,searchCriteria));
    }
}
