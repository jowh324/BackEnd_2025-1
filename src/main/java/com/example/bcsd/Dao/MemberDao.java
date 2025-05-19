package com.example.bcsd.Dao;

import com.example.bcsd.Model.Member;

import java.util.List;

public interface MemberDao {
    List<Member> findByBoardId(long member_id);

    Member findById(long id);

    Member insert(Member member);

    int update(Member member);

    int delete(long id);


}
