package com.example.money_management.controller;

import com.example.money_management.dto.HistoryDTO;
import com.example.money_management.dto.LimitMoneyDTO;
import com.example.money_management.dto.RoomList;
import com.example.money_management.entity.LimitMoney;
import com.example.money_management.service.HistoryService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


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

    @GetMapping("/index")
    public String showMainPage(@SessionAttribute(value = "member", required = false) String member, Model model){
        log.info("index............. GET");
        log.info("/index............ " + member);

        if(member == null){
            return "redirect:/money_management/login";
        }

        return "index";
    }

    @GetMapping("/statistic")
    public String showPage(){
        return "statistic";
    }

    @ResponseBody
    @PostMapping("/save")
    public String[] save(@RequestBody HistoryDTO dto){ //HashMap<String, Object> map
        log.info("save.......... ");


        String id = (String) httpSession.getAttribute("member");
        System.out.println(dto);
        Integer content_no = historyService.getTotalCount(id, dto.getYear(), dto.getMonth(), dto.getDate());

        if(content_no == null) content_no = 0;
        else content_no++;

        dto.setId(id);
        dto.setContent_no(content_no);

        System.out.println("==================");
        System.out.println(dto);

        historyService.saveHistory(dto);

        return new String[]{"success"};
    }

    @PostMapping("save/limit_money")
    public @ResponseBody Boolean render(@RequestBody LimitMoneyDTO map){
        map.setId((String)httpSession.getAttribute("member"));
        Boolean queryResponse = historyService.saveLimitMoney(map);
        return queryResponse;
    }

    @GetMapping("/share_plan")
    public String showShare_plan(){
        log.info("share_plan.................");

        return "share_plan";
    }

    @GetMapping("/lobby")
    public String showLobby(Model model){
        log.info("lobby.......................");
        String id = (String)httpSession.getAttribute("member");
        if(id == null) return "redirect:/money_management/login";

        //RoomList 객체의 list를 불러와서 모델에 저장한다
        model.addAttribute(roomList.getRoomList());

        return "lobby";
    }
}
