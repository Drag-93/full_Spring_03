package org.spring.springmvc2.controller;

import lombok.RequiredArgsConstructor;
import org.spring.springmvc2.dto.MemberDto;
import org.spring.springmvc2.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/memberList")
    public String memberList(Model model){
        List<MemberDto> memberList=memberService.memberList();
        model.addAttribute("memberList",memberList);

        return "/member/memberList";
    }

    //회원가입 페이지 이동
    @GetMapping("/join")
    public String join(){
        return "member/join";
    }

    @PostMapping("/join")
    public String joinOk(@ModelAttribute MemberDto memberDto){
        memberService.memberInsert(memberDto);
        System.out.println("로그인페이지로 이동");
        return "redirect:/member/login";
    }
    @GetMapping("/login")
    public String login(){

        return "member/login";
    }
}
