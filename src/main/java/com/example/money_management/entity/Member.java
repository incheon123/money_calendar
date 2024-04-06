package com.example.money_management.entity;

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
@Log4j2
@ToString
public class Member extends TimeLog{

    @Id
    @Column(name = "MEMBER_ID")
    private String id;

    private String pw;

    private String email;

    private String gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "rid")
    private List<Room> currentRooms = new ArrayList<>();
}
