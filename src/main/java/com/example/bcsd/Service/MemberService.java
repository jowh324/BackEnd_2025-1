package com.example.bcsd.Service;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Dto.*;
import com.example.bcsd.Model.Member;
import com.example.bcsd.exception.Emailexception;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class MemberService {
    private final ArticleDao articleDao;
    private final MemberDao memberDao;
    private final PasswordEncoder passwordEncoder;
    public MemberService(MemberDao memberDao, ArticleDao articleDao, PasswordEncoder passwordEncoder) {
        this.memberDao = memberDao;
        this.articleDao = articleDao;
        this.passwordEncoder = passwordEncoder;
    }
    public MemberResponse findmemberById(long id) {
        if(memberDao.findById(id)==null){
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }
        Member member= memberDao.findById(id);

        return MemberResponse.of(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPassword()
        );
    }
    @Transactional
    public MemberResponse insertMember(MemberCreate member) {
        if(memberDao.findByEmail(member.email())!=null){
            throw new Emailexception(member.email());
        }

        Member insert=new Member();
        insert.setName(member.name());
        insert.setEmail(member.email());
        insert.setPassword(member.password());

        Member save = memberDao.insert(insert);
        return MemberResponse.of(
                save.getId(),
                save.getName(),
                save.getEmail(),
                save.getPassword()
        );
    }
    @Transactional
    public MemberResponse updateMember(MemberUpdate update,Long id) {
        if(memberDao.findById(id)==null){
            throw new EntityNotFoundException("Member with id: " + id + " not found!");
        }

        Member update1=memberDao.findById(id);
        update1.setName(update.name());
        update1.setEmail(update.email());
        update1.setPassword(update.password());

        memberDao.update(update1);
        return MemberResponse.of(
                update1.getId(),
                update1.getName(),
                update1.getEmail(),
                update1.getPassword()
        );
    }
    @Transactional
    public void deleteMember(Long id) {
        if (!articleDao.findByAuthorId(id).isEmpty()) {
            throw new EntityExistsException("사용자가 작성한 게시물이 존재합니다.");
        }

        boolean deleted = memberDao.delete(id);
        if (!deleted) {
            throw new EntityNotFoundException("해당 id가 존재하지 않습니다.");
        }

    }
    @Transactional
    public LoginResponse login(loginRequest loginRequest) {
        Member member=memberDao.findByEmail(loginRequest.email());
        if(member==null){
            throw new Emailexception(loginRequest.email());
        }
        if(!passwordEncoder.matches(loginRequest.password(), member.getPassword())){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");        }
        return LoginResponse.of(member.getEmail());
    }



}
