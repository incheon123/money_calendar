package com.example.money_management.service;

import com.example.money_management.dto.MemberDTO;
import com.example.money_management.entity.History;
import com.example.money_management.entity.Member;
import com.example.money_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member findById(MemberDTO memberDTO) {

        Optional<Member> result = memberRepository.findById(memberDTO.getId());

        //만약 result가 비었다면(아이디가 없다면)
        if(result.isEmpty()) { return null;}

        Member m = result.get();

        //만약 가져온 member의 비밀번호와 요청된 객체의 비밀번호가 다르다면
        if(!( m.getPw().equals(memberDTO.getPw())) ){
            return null;
        }

        return m;
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
    public void save(MemberDTO memberDTO) {
        System.out.println(memberDTO);
        Member member = dtoToEntity(memberDTO);
        memberRepository.save(member);
    }

}
