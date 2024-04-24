package com.example.money_management.dto;

import com.example.money_management.entity.RoomHistory;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Room{
    
    //방 아이디
    @Id
    private Long rid;

    //방 제목
    private String title;

    //방
    private List<RoomHistory> roomHistories = new ArrayList<>();

    //채팅 내역
    private List<ChatMessage> chatList = new ArrayList<>();

    //최대인원
    private int maxCapacity;

    //현재 방 인원
    private int currentPeopleCount;

    //비밀번호여부
    private boolean isSetPw;

    // 방 비밀번호
    private String password;

    // public void generateId(){
    //     this.rid = ThreadLocalRandom.current().nextLong(4, Long.MAX_VALUE);
    // }

}
