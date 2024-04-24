package com.example.money_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RoomHistoryId.class)
@ToString(exclude = {"room", "member"})
@Getter
public class RoomHistory extends TimeLog{

    @Id
    @ManyToOne
    @JoinColumn(name = "room_id")
    @JsonBackReference
    private Room room;

    @Id
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean isCreater;
}
