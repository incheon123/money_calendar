package com.example.money_management.entity;

import com.example.money_management.dto.MemberDTO;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ChatMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private long order;

    @ManyToOne
    private Member describer;

    @ManyToOne
    private Room room;

    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;
    private String msg;
}
