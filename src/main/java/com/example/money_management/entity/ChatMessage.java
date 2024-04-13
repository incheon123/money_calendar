package com.example.money_management.entity;

import com.example.money_management.dto.MemberDTO;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ChatMessage {

    @Id @GeneratedValue
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member sender;

    @JoinColumn(name = "room_id")
    @ManyToOne
    private Room room;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;
    private String msg;
}
