package com.example.money_management.service;

import com.example.money_management.dto.MemberDTO;
import com.example.money_management.entity.Member;

public interface MemberService{
    /**
     * 전달받은 member_id를 가지고 있는 Member entity를 반환
     * @param member_id
     * @return Member
     */
    Member findById(String member_id);

    String findIdByEmail(String email);
    String findPwByIdAndEmail(String id, String email);

    Member save(MemberDTO member);

    Member compareIdAndPw(MemberDTO memberDTO);

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
