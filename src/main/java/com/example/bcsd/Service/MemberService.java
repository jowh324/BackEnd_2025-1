package com.example.bcsd.Service;

import com.example.bcsd.Dao.ArticleDao;
import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Dto.MemberCreate;
import com.example.bcsd.Dto.MemberResponse;
import com.example.bcsd.Dto.MemberUpdate;
import com.example.bcsd.Model.Member;
import com.example.bcsd.exception.Emailexception;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class MemberService {
    private final ArticleDao articleDao;
    private final MemberDao memberDao;
    public MemberService(MemberDao memberDao, ArticleDao articleDao) {
        this.memberDao = memberDao;
        this.articleDao = articleDao;
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


}
