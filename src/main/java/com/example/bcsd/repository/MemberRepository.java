package com.example.bcsd.repository;

import com.example.bcsd.Dao.MemberDao;
import com.example.bcsd.Model.Member;
import com.example.bcsd.mapper.membermapper;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberRepository implements MemberDao {
    private final JdbcTemplate jdbcTemplate;

    public MemberRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public Optional<Member> findById(long id) {

        String sql = "SELECT * FROM member where id=? ";
        List<Member> members = jdbcTemplate.query(sql, new membermapper(), id);
        return members.stream().findFirst();

    }

    @Override
    public Member insert(Member member) {
        String sql = """
                INSERT INTO member
                (name,email,password) 
                values ( ?,?,?)
                """;
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, member.getName());
            ps.setString(2, member.getEmail());
            ps.setString(3, member.getPassword());

            return ps;
        }, keyHolder);
        if (keyHolder != null) {
            member.setId(keyHolder.getKey().longValue());
        } else {
            throw new DataRetrievalFailureException("Failed to retrieve generated key for Member");
        }
        return member;
    }

    public int update(Member member) {
        String sql = """
                UPDATE member
                   SET name = ?, email = ?, password = ?
                 WHERE id = ?
                """;
        return jdbcTemplate.update(sql, member.getName(), member.getEmail(), member.getPassword(), member.getId());
    }

    public boolean delete(long id) {
        return jdbcTemplate.update("delete from member where id = ?", id)>0;
    }
}
