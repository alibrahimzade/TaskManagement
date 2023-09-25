package com.example.taskmanagement.dao.repository;

import com.example.taskmanagement.dao.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {
    Optional<Item> getItemByName(String name);
}
