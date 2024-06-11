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

    @Id
    private Long msg_id;

    @JoinColumn(name = "sender")
    @ManyToOne
    private Member sender;

    @JoinColumn(name = "receiver")
    @ManyToOne
    private Room receiver;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    private String msg;
    private String type;

    @PrePersist
    private void createMsgId(){

    }
}
