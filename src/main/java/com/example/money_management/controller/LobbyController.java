package com.example.money_management.controller;

import com.example.money_management.dto.MemberDTO;
import com.example.money_management.dto.RoomList;
import com.example.money_management.entity.Member;
import com.example.money_management.entity.Room;
import com.example.money_management.entity.RoomHistory;
import com.example.money_management.enumType.RoomType;
import com.example.money_management.repository.MemberRepository;
import com.example.money_management.repository.RoomHistoryRepository;
import com.example.money_management.repository.RoomRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
@Log4j2
@RequestMapping("/money_management")
public class LobbyController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RoomList roomList;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoomHistoryRepository roomHistoryRepository;

    @GetMapping("/lobby")
    public String showLobby(Model model){
        log.info("lobby.......................");
        String id = (String)httpSession.getAttribute("member");
        if(id == null) return "redirect:/money_management/login";

        // 채팅방 목록 가져오기(private 제외)
        List<Room> rooms = roomRepository.getRoomsByType(RoomType.CHATTING);
        log.info(rooms);

        model.addAttribute("rooms", rooms);

        return "lobby";
    }

    
}
