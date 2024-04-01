package com.example.money_management.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Room {
    //방 아이디
    private long id;

    //방 제목
    private String title;

    //방장
    private MemberDTO creater;

    //방 참여자
    private List<MemberDTO> participants;

    private List<ChatMessage> chatList;

    //최대인원
    private int maxCapacity;

    //비밀번호여부
    private boolean isSetPw;

    // 방 비밀번호
    // isSetPw가 true라면 password 세팅
    private String password;

    public void generateId(){
        this.id = ThreadLocalRandom.current() .nextLong(4, Long.MAX_VALUE);
    }
}
