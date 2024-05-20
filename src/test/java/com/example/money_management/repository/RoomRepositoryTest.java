package com.example.money_management.repository;

import groovy.util.logging.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.money_management.entity.Room;
import com.example.money_management.entity.RoomHistory;

@SpringBootTest
@Log4j2
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired RoomHistoryRepository roomHistoryRepository;

    @Test
    @Transactional
    public void testGetMember(){
        Room r = roomRepository.getReferenceById(1529011751015906920L);
        System.out.println(r);
//        System.out.println(r.getRoomHistories().get(0).getMember());
    }

    @Test
    @Transactional
    public void testGetRoomHistory(){
        RoomHistory rh = roomHistoryRepository.getReferenceById(null);
        roomRepository.getReferenceById(null);
    }
}
