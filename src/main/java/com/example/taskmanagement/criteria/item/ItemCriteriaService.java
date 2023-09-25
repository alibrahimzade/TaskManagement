package com.example.taskmanagement.criteria.item;

import com.example.taskmanagement.dao.entity.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCriteriaService {
    private final ItemCriteriaRepository criteriaRepository;

    public Page<Item> getItemWithCriteria(ItemPage itemPage, ItemSearchCriteria searchCriteria){
        return criteriaRepository.getItemsWithCriteria(itemPage,searchCriteria);
    }
}
