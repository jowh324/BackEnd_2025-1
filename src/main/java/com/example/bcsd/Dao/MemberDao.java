package com.example.bcsd.Dao;

import com.example.bcsd.Model.Member;

import java.util.List;
import java.util.Optional;

public interface MemberDao {

    Member findById(long id);
    Member findByEmail(String email);

    Member insert(Member member);

    Member update(Member member);

    boolean delete(long id);


}
