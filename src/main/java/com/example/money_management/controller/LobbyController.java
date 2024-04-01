package com.example.money_management.controller;

import com.example.money_management.dto.MemberDTO;
import com.example.money_management.dto.Room;
import com.example.money_management.dto.RoomList;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

@Controller
@Log4j2
@RequestMapping("/money_management")
public class LobbyController {

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RoomList roomList;

    @PostMapping("/create/room")
    public String createRoom(Room room){
        log.info("createRoom.................. " + httpSession.getAttribute("member"));
        String id = (String)httpSession.getAttribute("member");
        if(id == null) return "redirect:/money_management/login";

        //create
        room.setCreater(new MemberDTO(id));
        room.generateId();


        roomList.add(room);

        return "redirect:/money_management/room/" + room.getId();
    }


//    log.info("shit " + URLEncoder.encode(String.valueOf(rid), UTF_8));
    @GetMapping("/room/{id}")
    public String test(@PathVariable("id") String id) throws UnsupportedEncodingException {
        long rid = (long)Long.parseLong(id);
        log.info(roomList.getRoom(rid));

        return "room";
    }
}
