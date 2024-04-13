package com.example.money_management.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@IdClass(RoomHistoryId.class)
public class RoomHistory {

    @Id
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    private Date joinDate;
}
