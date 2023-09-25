package com.example.taskmanagement.service;

import com.example.taskmanagement.dao.entity.Member;
import com.example.taskmanagement.dao.repository.MemberRepository;
import com.example.taskmanagement.model.dto.MemberDto;
import com.example.taskmanagement.model.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

import static org.hibernate.engine.spi.Status.DELETED;
import static org.hibernate.engine.spi.Status.SAVING;
import static org.springframework.http.HttpStatus.*;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public ResponseEntity<?> getMemberByName(String name) {
        Member member = memberRepository.getMemberByName(name).orElseGet(() -> null);
        if (Objects.nonNull(member)) {
            return ResponseEntity.ok(MemberMapper.INSTANCE.mapEntityToDto(member));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member doesn't exist");
    }

    public ResponseEntity<?> getMemberById(Long id) {
        Member member = memberRepository.findById(id).orElseGet(() -> null);
        if (Objects.nonNull(member)) {
            return ResponseEntity.ok(MemberMapper.INSTANCE.mapEntityToDto(member));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member doesn't exist");
    }

    public ResponseEntity<?> createMember(MemberDto memberDto) {
        Member memberName = memberRepository.getMemberByName(memberDto.getName()).orElseGet(() -> null);
        if (Objects.isNull(memberName)) {
            Member member = MemberMapper.INSTANCE.mapDtoToEntity(memberDto);
            memberRepository.save(member);
            return ResponseEntity.ok(SAVING);
        }
        return ResponseEntity.status(CONFLICT).body("Member is already exist");
    }

    public ResponseEntity<?> updateMember(MemberDto memberDto, Long memberId){
        Member member = memberRepository.findById(memberId).orElseGet(() -> null);
        if (Objects.nonNull(member)){
            Member updated = MemberMapper.INSTANCE.mapDtoToEntity(memberDto);
            updated.setName(updated.getName());

            memberRepository.save(updated);
            return ResponseEntity.status(OK).body("Member is updated");
        }
        return ResponseEntity.status(NOT_FOUND).body("Member doest exist");
    }

    public ResponseEntity<?> deleteMember(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            memberRepository.deleteById(id);
            return ResponseEntity.status(OK).body(DELETED);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member doesn't exist");
    }
}
