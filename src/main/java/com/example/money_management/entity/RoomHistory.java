package com.example.money_management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(RoomHistoryId.class)
@ToString(exclude = {"room", "member"})
@Getter
public class RoomHistory {

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
    private Date joinDate;
}
