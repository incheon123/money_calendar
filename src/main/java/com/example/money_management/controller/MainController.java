package com.example.money_management.controller;

import com.example.money_management.dto.RoomList;
import com.example.money_management.entity.Member;
import com.example.money_management.entity.RoomHistory;
import com.example.money_management.repository.MemberRepository;
import com.example.money_management.repository.RoomRepository;
import com.example.money_management.service.HistoryService;
import com.example.money_management.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping(value = "/money_management")
@Log4j2
@SessionAttributes("member")
public class MainController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RoomList roomList;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @GetMapping("/myCalendar")
    public String redirectPage(@SessionAttribute(value = "member") String member){
        Member m = memberService.findById(member);
        return "redirect:/money_management/myCalendar/" + m.getPrivateRoomId();
    }
    @GetMapping("/myCalendar/{rid}")
    public String showMainPage(@SessionAttribute(value = "member", required = false) String member,
                               @PathVariable("rid") Long rid){
        log.info("index............. GET");
        log.info("/index............ " + member);

        if(member == null) return "redirect:/money_management/login";


        return "myCalendar";
    }

    @PostMapping("/myCalendar")
    @ResponseBody
    public List<RoomHistory> getIndexCalendar(@SessionAttribute(value = "member") String member){
        log.info("getIndexCalendar");

        Long rid = memberRepository.getReferenceById(member).getPrivateRoomId();
        List<RoomHistory> roomHistories = roomRepository.getReferenceById(rid).getRoomHistories();
        System.out.println(roomHistories);
        return roomHistories;
    }

    @GetMapping("/statistic")
    public String showPage(){
        return "statistic";
    }

    @GetMapping("/share_plan")
    public String showShare_plan(){
        log.info("share_plan.................");

        return "share_plan";
    }
    
}
