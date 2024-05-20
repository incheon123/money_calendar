package com.example.money_management.dto;

import com.example.money_management.annotation.DtoToEntity;
import com.example.money_management.entity.Member;
import com.example.money_management.enumType.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDTO {
    private String owner;
    private String pw;
    private String email;
    private String gender;
    private Role role;
    private List<Room> currentRooms;
    private Long privateRoomId;

    public MemberDTO(String owner){
        this.owner = owner;
        this.role = Role.MEMBER;
        currentRooms = new ArrayList<>();
    }
}
