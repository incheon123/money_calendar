package com.example.money_management.entity;

import com.example.money_management.enumType.RoomType;
import com.example.money_management.money_interface.Generable;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"chatList"})
@Builder
@Setter
@Getter
@Component
public class Room extends TimeLog implements Generable, Serializable{
    //방 아이디
    @Id
    @Column(name = "room_id")
    private Long rid;

    //방 제목
    @Column(name = "TITLE")
    private String title;

    @Column(name = "owner")
    private String owner;

    //방
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<RoomHistory> roomHistories = new ArrayList<>();

    @Column(name = "CHAT_LIST")
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<ChatMessage> chatList = new ArrayList<>();

    //최대인원
    @Column(name = "MAX_CAPACITY")
    private int maxCapacity;

    @Column(name = "current_people_count")
    private int currentPeopleCount;

    //비밀번호여부
    @Column(name = "IS_SET_PW")
    private boolean isSetPw;

    // 방 비밀번호
    // isSetPw가 true라면 password 세팅
    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROOM_TYPE")
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
    @Transient
    private String requestRoomType;

    @PrePersist
    public void init(){
        this.currentPeopleCount = 1;
        this.rid = generateId(); // 영속 전에 rid 생성
        if(password != null) isSetPw = true;

        this.roomType = requestRoomType.equals("CHATTING") 
                        ? RoomType.CHATTING : RoomType.PRIVATE;
    }
}
