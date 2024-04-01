package com.example.money_management.repository;

import com.example.money_management.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository repository;

    @Test
    public void saveTest(){
        Member member = Member.builder()
                .id("opportunity13")
                .pw("rlghlek153")
                .email("opportunity13@naver.com")
                .gender("man")
                .build();


        //save할 때 이미 있는 값이면 update, 없는 값이면 insert
        //pk가 중복된다고 에러 나지 않는다.
        repository.save(member);
    }

    @Test
    public void removeTest(){
        Member member = Member.builder()
                .id("opportunity13")
                .build();

        repository.delete(member);
    }
}
