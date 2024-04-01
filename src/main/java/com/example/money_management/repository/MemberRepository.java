package com.example.money_management.repository;

import com.example.money_management.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, String> {

    @Query("select m.id from Member m where m.email = :email")
    String findEmail(@Param("email") String email);

    @Query("select m.pw from Member m where m.id = :id and m.email = :email")
    String findPw(@Param("id") String id, @Param("email") String email);
}
