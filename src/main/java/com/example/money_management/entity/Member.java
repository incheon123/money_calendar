package com.example.money_management.entity;

import com.example.money_management.annotation.DtoToEntity;
import com.example.money_management.dto.RoomList;
import com.example.money_management.enumType.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString(exclude = {"currentRooms"})
public class Member extends TimeLog{

    @Id
    @Column(name = "member_id", nullable = false)
    private String id;

    @Column(nullable = false)
    private String pw;

    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private Long privateRoomId;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<RoomHistory> currentRooms = new ArrayList<>();
}
