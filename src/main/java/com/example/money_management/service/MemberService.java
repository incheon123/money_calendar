package com.example.money_management.service;

import com.example.money_management.dto.MemberDTO;
import com.example.money_management.entity.Member;

public interface MemberService{
    Member findById(MemberDTO memberDTO);

    String findIdByEmail(String email);
    String findPwByIdAndEmail(String id, String email);

}
