package com.example.bcsd.controller;


import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Dto.ArticleUpdate;
import com.example.bcsd.Dto.MemberCreate;
import com.example.bcsd.Dto.MemberResponse;
import com.example.bcsd.Dto.MemberUpdate;
import com.example.bcsd.Model.Member;

import com.example.bcsd.Service.MemberService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Map;
import java.util.Optional;

@RestController
public class MemberController {
    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/member")
    public ResponseEntity<MemberResponse> addMember(@Valid @RequestBody MemberCreate member) {
        MemberResponse newMember = memberService.insertMember(member);
        return ResponseEntity.created(URI.create("/"+newMember.id())).body(newMember);
    }
    @PutMapping("/member/{id}")
    public ResponseEntity<MemberResponse> updateMember(@PathVariable Long id, @RequestBody MemberUpdate update) {
        MemberResponse reponse= memberService.updateMember(update,id);

        // 변경된 정보를 다시 조회해서 돌려줄 수도 있습니다.
        return ResponseEntity.ok(reponse);
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
       memberService.deleteMember(id);

        return ResponseEntity.noContent().build();
    }
}
