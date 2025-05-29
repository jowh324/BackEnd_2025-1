package com.example.bcsd.repository;

import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Model.Article;
import com.example.bcsd.Model.Board;
import com.example.bcsd.Model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository implements MemberDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public Member findById(long id) {

        return em.find(Member.class, id);

    }
    @Override
    public Member findByEmail(String email) {

        String jpql = "select m from Member m where m.email=:email";
        try{ return em.createQuery(jpql, Member.class).setParameter("email",email).getSingleResult();}
        catch (NoResultException e){
            return null;
        }

    }

    @Override
    public Member insert(Member member) {

        em.persist(member);
        return member;
    }

    public Member update(Member member) {
        return em.merge(member);
    }

    public boolean delete(long id) {
        Board b = em.find(Board.class, id);
        if (b != null) {
            em.remove(b);
            return true;
        }
        return false;
    }
}
