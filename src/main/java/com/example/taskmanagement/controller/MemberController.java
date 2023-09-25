package com.example.taskmanagement.controller;

import com.example.taskmanagement.model.dto.MemberDto;
import com.example.taskmanagement.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/byId/{memberId}")
    public ResponseEntity<?> getMemberById(@PathVariable(name = "memberId") Long id){
        return memberService.getMemberById(id);
    }
    @GetMapping("/byName/{name}")
    public ResponseEntity<?> getMemberByName(@PathVariable(name = "name") String name){
        return memberService.getMemberByName(name);
    }
    @PostMapping("/createMember")
    public ResponseEntity<?> createMember(@RequestBody MemberDto memberDto){
        return memberService.createMember(memberDto);
    }
    @PutMapping("/updateMember/{memberId}")
    public ResponseEntity<?> updateMember(@RequestBody MemberDto memberDto,
                                          @PathVariable(name = "memberId") Long memberId){
        return memberService.updateMember(memberDto,memberId);
    }
    @DeleteMapping("/deleteMember/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable(name = "memberId") Long Id){
        return memberService.deleteMember(Id);
    }
}
