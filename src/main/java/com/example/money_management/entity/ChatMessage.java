package com.example.money_management.entity;

import com.example.money_management.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ChatMessage {

    @Id @GeneratedValue
    private Long id;

    @JoinColumn(name = "member_id")
    @ManyToOne
    private Member sender;

    @JoinColumn(name = "room_id")
    @ManyToOne
    private Room chatRoom;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;
    private String msg;
}
