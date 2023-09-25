package com.example.taskmanagement.image.repository;

import com.example.taskmanagement.dao.entity.Member;
import com.example.taskmanagement.image.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image,Long> {
    Optional<Image> findByName(String name);
}
