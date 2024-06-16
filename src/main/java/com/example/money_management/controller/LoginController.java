package com.example.money_management.controller;

import com.example.money_management.dto.MemberDTO;
import com.example.money_management.entity.Member;
import com.example.money_management.entity.Room;
import com.example.money_management.entity.RoomHistory;
import com.example.money_management.enumType.Role;
import com.example.money_management.repository.MemberRepository;
import com.example.money_management.repository.RoomHistoryRepository;
import com.example.money_management.repository.RoomRepository;
import com.example.money_management.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@RequestMapping("/money_management")
@SessionAttributes("member")
@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private RoomHistoryRepository roomHistoryRepository;

    @Autowired
    private ApplicationContext context;


    /**
     * login GET method
     * @param member
     * @return
     */
    @GetMapping({"", "/login"})
    public String showLoginPage(@SessionAttribute(name = "member", required = false) String member){
        log.info("login........GET");
        log.info("login.................... id " + "session id : " + member);
        log.info("login.................... id " + "session id : " + httpSession.getAttribute("member"));
        return "login";
    }

    /**
     * login POST method
     * @param memberDTO
     * @param redirectAttributes
     * @return
     */
    @PostMapping( "/login")
    public String login(@ModelAttribute("login_member") MemberDTO memberDTO,
                        RedirectAttributes redirectAttributes,
                        Model model){
        log.info("login............. POST");

        //원래는 준영속상태지만 OSIV가 true이므로 영속상태
        //컨트롤러단에서는 준영속인데 영속으로 만들어줌(설정파일에서)
        Member member = memberService.compareIdAndPw(memberDTO);

        //member가 null이라면
        if(member == null) {
            System.out.println("no-matching id");
            redirectAttributes.addFlashAttribute("msg", "no-matching id");
            return "redirect:/money_management/login";
        }
        Long rid = member.getPrivateRoomId();
        model.addAttribute("member", member.getId());

        return "redirect:/money_management/myCalendar/" + rid;
    }

    /**
     * logout method
     * @param sessionStatus
     * @param member
     * @return
     */
    @PostMapping("/logout")
    public String logout(@SessionAttribute(value = "member", required = false) String member, SessionStatus sessionStatus){
        log.info("logout...................");


        sessionStatus.setComplete();
        log.info("logout.................... id " + httpSession.getAttribute("member"));
        return "redirect:/money_management/login";
    }


    @GetMapping("/register")
    public String showRegisterPage(){
        return "register";
    }

    /*
        계정정보를 받아와 등록한다
     */
    @PostMapping("/register")
    public String register(MemberDTO memberDTO, Room room){
        log.info("create private room.................. [{}]", memberDTO.getOwner());
        memberDTO.setRole(Role.MEMBER);

        //create
        Room savedRoom = roomRepository.save(room);
        memberDTO.setPrivateRoomId(savedRoom.getRid());
        Member member = memberService.save(memberDTO);

        RoomHistory roomHistory = RoomHistory.builder()
                .room(savedRoom)
                .member(member)
                .isCreater(true) //true인 이유는 지금 이 메서드가 방 만드는 메서드기 때문
                .build();

        roomHistoryRepository.save(roomHistory);



        return "redirect:/money_management/login";
    }

    @GetMapping("/findId")
    public String showFindIdPage(){
        return "findId";
    }

    @PostMapping("/findId")
    public @ResponseBody String findId(@RequestParam("email") String email){
        log.info("email은 " + email);

        String result = memberService.findIdByEmail(email);

        if(result != null) return result;
        return "error";
    }

    @GetMapping("/findPw")
    public String showFindPwPage(){
        return "findPw";
    }

    @PostMapping("/findPw")
    public @ResponseBody String findPw(@RequestParam("id") String id, @RequestParam("email") String email){

        String pw = memberService.findPwByIdAndEmail(id, email);

        if(pw != null) return pw;

        return "error";
    }
}
