package com.example.money_management.entity;

import com.example.money_management.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest
public class RoomTests {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    public void RoomTest(){
        Room room = Room.builder()
                .rid(123L)
                .title("room#123")
                .maxCapacity(5)
                .isSetPw(true)
                .password("12345")
                .build();

        roomRepository.save(room);
    }

    @Test
    @Transactional
    public void getRoomTest(){
        Optional<Room> room = roomRepository.findById(123L);
        if(room.isPresent())
            System.out.println(room.get().getRoomHistories().size());
//            for(RoomHistory history : room.get().getRoomHistories()){
//                System.out.println(history);
//            }

    }
}


/*
    1. 사용자가 방을 생성하면 Room entity에 정보를 저장하고 repository.save()
    2. 사용자가 검색하면 현재인원수/최대인원수, 방장 등의 방 정보를 보여줌
    3. 사용자가 검색하면 검색 키워드에 맞는 방의 개수를 보여준다
    
    Room DTO를 만들어서 받는다
 */