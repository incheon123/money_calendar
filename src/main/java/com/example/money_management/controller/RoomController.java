package com.example.money_management.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.money_management.entity.Member;
import com.example.money_management.entity.Room;
import com.example.money_management.entity.RoomHistory;
import com.example.money_management.repository.MemberRepository;
import com.example.money_management.repository.RoomHistoryRepository;
import com.example.money_management.repository.RoomRepository;

import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/money_management")
public class RoomController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoomHistoryRepository roomHistoryRepository;

    /*
     * 방생성 컨트롤러 메서드
     */
    @PostMapping("/create/room")
    public String createRoom(Room room){
        log.info("createRoom.................. " + httpSession.getAttribute("member"));
        String id = (String)httpSession.getAttribute("member");
        room.setOwner(id);
        if(id == null) return "redirect:/money_management/login";

        //create
        Room savedRoom = roomRepository.save(room);
        Member member = memberRepository.getReferenceById(id);
        RoomHistory roomHistory = RoomHistory.builder()
                .room(savedRoom)
                .member(member)
                .isCreater(true) //true인 이유는 지금 이 메서드가 방 만드는 메서드기 때문
                .build();

        roomHistoryRepository.save(roomHistory);


        return "redirect:/money_management/room/"+room.getRid();
    }


    @GetMapping("/room/{id}")
    public String test(@PathVariable("id") String id) throws UnsupportedEncodingException {
        long rid = (long)Long.parseLong(id);
        log.info("rid => " + rid);
        

        return "room";
    }
}
