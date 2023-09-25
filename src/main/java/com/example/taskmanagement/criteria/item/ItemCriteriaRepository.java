package com.example.taskmanagement.criteria.item;

import com.example.taskmanagement.dao.entity.Item;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ItemCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ItemCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Item> getItemsWithCriteria(ItemPage itemPage ,ItemSearchCriteria searchCriteria){
        CriteriaQuery<Item> criteriaQuery= criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = criteriaQuery.from(Item.class);
        Predicate predicate = getPredicates(searchCriteria,itemRoot);
        criteriaQuery.where(predicate);
        setOrder(itemPage, criteriaQuery, itemRoot);

        TypedQuery<Item> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setMaxResults(itemPage.getPageSize());
        typedQuery.setFirstResult(itemPage.getPageNumber() * itemPage.getPageSize());

        Pageable pageable = getPageable(itemPage);
        long countItems = getCountItems(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, countItems);
    }
    private Predicate getPredicates(ItemSearchCriteria searchCriteria , Root<Item> itemRoot){
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(searchCriteria.getName())){
            predicates.add(criteriaBuilder.like(itemRoot.get("name"),"%"+searchCriteria.getName()+"%"));
        }
        if (Objects.nonNull(searchCriteria.getName())){
            predicates.add(criteriaBuilder.like(itemRoot.get("description"),"%"+searchCriteria.getDescription()+"%"));
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ItemPage itemPage, CriteriaQuery<Item> criteriaQuery , Root<Item> itemRoot){
        if (itemPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(itemRoot.get(itemPage.getSortBy())));
        } else
            criteriaQuery.orderBy(criteriaBuilder.desc(itemRoot.get(itemPage.getSortBy())));
    }

    private Pageable getPageable(ItemPage itemPage){
        Sort sort = Sort.by(itemPage.getSortDirection(),itemPage.getSortBy());
        return PageRequest.of(itemPage.getPageNumber(), itemPage.getPageSize(), sort);
    }
    private long getCountItems(Predicate predicate){
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<Item> itemRoot = criteriaQuery.from(Item.class);
        criteriaQuery.select(criteriaBuilder.count(itemRoot)).where(predicate);
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

}
