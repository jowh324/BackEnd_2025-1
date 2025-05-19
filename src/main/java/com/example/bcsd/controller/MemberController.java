package com.example.bcsd.controller;


import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Member;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MemberController {
    private MemberDao memberDao;
    public MemberController(MemberDao memberDao) {
        this.memberDao = memberDao;
    }

    @PostMapping("/member")
    public ResponseEntity<Member> addMember(@RequestBody Member member) {
        Member newMember = memberDao.insert(member);
        return ResponseEntity.ok(member);
    }
    @PutMapping("/member/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable Long id, @RequestBody Map<String, Object> payload) {

        Member member = memberDao.findById(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        member.setPassword((String) payload.get("password"));
        member.setEmail((String) payload.get("email"));
        member.setName((String) payload.get("name"));
        memberDao.update(member);
        // 변경된 정보를 다시 조회해서 돌려줄 수도 있습니다.
        return ResponseEntity.ok(memberDao.findById(id));
    }

    @DeleteMapping("/member/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        int row = memberDao.delete(id);
        if (row == 0) {
            ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
