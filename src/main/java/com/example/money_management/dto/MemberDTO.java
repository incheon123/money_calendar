package com.example.money_management.dto;

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
    private String id;
    private String pw;
    private String email;
    private String gender;
    private Role role;
    private List<Room> currentRooms;

    public MemberDTO(String id){
        this.id = id;
        currentRooms = new ArrayList<>();
    }
}
