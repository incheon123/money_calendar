package com.example.money_management.entity;

import com.example.money_management.enumType.Role;
import com.example.money_management.repository.MemberRepository;
import com.example.money_management.repository.RoomHistoryRepository;
import com.example.money_management.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class RoomHistoryTests {

    @Autowired
    private RoomHistoryRepository repository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void insertTest(){
        Room room = roomRepository.getReferenceById(123L);
        Member member = memberRepository.getReferenceById("cola2");

        RoomHistory rh = RoomHistory.builder()
                .room(room)
                .member(member)
                .isCreater(true)
                .build();

        repository.saveAndFlush(rh);
    }

    @Test
    @Transactional
    public void getTest(){
        Room room = roomRepository.getReferenceById(123L);
        Member member = memberRepository.getReferenceById("cola2");

        RoomHistoryId id = RoomHistoryId.builder()
                        .room(room)
                        .member(member)
                        .build();

        RoomHistory result = repository.getReferenceById(id);
        System.out.println(result);
    }
}
