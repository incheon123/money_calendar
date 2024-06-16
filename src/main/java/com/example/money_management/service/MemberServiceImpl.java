package com.example.money_management.service;

import com.example.money_management.annotation.Dto;
import com.example.money_management.dto.MemberDTO;
import com.example.money_management.entity.History;
import com.example.money_management.entity.Member;
import com.example.money_management.entity.Room;
import com.example.money_management.repository.MemberRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member findById(String member_id) {


        //만약 result가 비었다면(아이디가 없다면)
        if(member_id == null) { return null;}

        Optional<Member> m = memberRepository.findById(member_id);
        if(!m.isPresent()) return null;

        return m.get();
    }

    @Override
    public String findIdByEmail(String id) {

        return memberRepository.findEmail(id);
    }

    @Override
    public String findPwByIdAndEmail(String id, String email) {

        return memberRepository.findPw(id, email);
    }

    /*
        회원가입한 계정을 db에 등록
     */
    @Override
    public Member save(MemberDTO memberDTO) {
        Member member = memberRepository.save(dtoToEntity(memberDTO));

        
        return member;
    }

    @Override
    @Transactional
    public Member compareIdAndPw(MemberDTO memberDTO) {

        
        //아이디를 찾든 못찾든 NULL은 아님...
        Optional<Member> result = memberRepository.findById(memberDTO.getOwner());
        
        Member m = null;
        if(result.isPresent()){
            m = result.get();
            if( !(m.getPw().equals(memberDTO.getPw()))) return null;
            return m;
        }

        return null;
    }

}
