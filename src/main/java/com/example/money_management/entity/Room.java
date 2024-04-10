package com.example.money_management.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Getter
public class Room {
    //방 아이디
    @Id
    @Column(name = "room_id")
    private long rid;

    //방 제목
    @Column(name = "TITLE")
    private String title;

    //방장
//    @Column(name = "CREATER")
    @JoinColumn(name = "CREATER_ID")
    @OneToOne
    private Member creater;



    //방 참여자
    @Column(name = "PARTICIPANTS")
    @OneToMany(mappedBy = "id")
    private List<Member> participants = new ArrayList<>();

    @Column(name = "CHAT_LIST")
    @OneToMany(mappedBy = "room")
    private List<ChatMessage> chatList = new ArrayList<>();

    //최대인원
    @Column(name = "MAX_CAPACITY")
    private int maxCapacity;

    //비밀번호여부
    @Column(name = "IS_SET_PW")
    private boolean isSetPw;

    // 방 비밀번호
    // isSetPw가 true라면 password 세팅
    @Column(name = "PASSWORD")
    private String password;

    public void generateId(){
        this.rid = ThreadLocalRandom.current() .nextLong(4, Long.MAX_VALUE);
    }
}
