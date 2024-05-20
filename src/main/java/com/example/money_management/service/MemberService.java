package com.example.money_management.service;

import com.example.money_management.dto.MemberDTO;
import com.example.money_management.entity.Member;

public interface MemberService{
    Member findById(MemberDTO memberDTO);

    String findIdByEmail(String email);
    String findPwByIdAndEmail(String id, String email);

    Member save(MemberDTO member);

    default Member dtoToEntity(MemberDTO dto){
        Member member = Member.builder()
                .id(dto.getOwner())
                .pw(dto.getPw())
                .email(dto.getEmail())
                .gender(dto.getGender())
                .role(dto.getRole())
                .privateRoomId(dto.getPrivateRoomId())
                .build();

        return member;
    }
}
