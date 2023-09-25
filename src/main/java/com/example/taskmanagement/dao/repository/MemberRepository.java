package com.example.taskmanagement.dao.repository;

import com.example.taskmanagement.dao.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> getMemberByName(String name);
}
