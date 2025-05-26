package com.example.bcsd.Dao;

import com.example.bcsd.Model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDao {

    Optional<Member> findById(long id);

    Member insert(Member member);

    int update(Member member);

    boolean delete(long id);


}
