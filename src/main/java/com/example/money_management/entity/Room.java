package com.example.money_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
@ToString(exclude = {"chatList"})
@Builder
@Setter
public class Room extends TimeLog{
    //방 아이디
    @Id
    @Column(name = "room_id")
    private Long rid;

    //방 제목
    @Column(name = "TITLE")
    private String title;

    //방
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RoomHistory> roomHistories = new ArrayList<>();

    @Column(name = "CHAT_LIST")
    @OneToMany(mappedBy = "chatRoom", fetch = FetchType.LAZY)
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
