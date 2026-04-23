package org.spring.springmvc2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.spring.springmvc2.dto.MemberDto;
import org.spring.springmvc2.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Member;
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
    public String join(MemberDto memberDto){
        return "member/join";
    }
    //회원가입 실행-> 유효성 검사
    @PostMapping("/join")
    public String joinOk(@Valid MemberDto memberDto, BindingResult bindingResult){
        //에러가 나면
        if(bindingResult.hasErrors()){
            return "/member/join";
        }

        memberService.memberInsert(memberDto);
//        System.out.println("로그인페이지로 이동");
//        return "redirect:/member/login";

        return "redirect:/member/login";
    }
    @GetMapping("/login")
    public String login(){

        return "member/login";
    }
    @PostMapping("/login")
    public String loginOk(@ModelAttribute MemberDto memberDto){
        MemberDto member =memberService.memberLogin(memberDto);

        return "redirect:/member/detail/"+member.getId();
    }
//    @{/member/detail/{id}(id=${member.id})}->/member/detail/값
    @GetMapping("/detail/{id}") //REST방식(나중), 권장 -> thymeleaf 쓸 때 사용
    public String detail(@PathVariable("id")Long id,Model model){
        MemberDto member=memberService.memberDetail(id);
        model.addAttribute("member",member);    
        return "member/detail";  //member/detail.html(회원상세페이지)
    }

//     @{/member/detail(id=${member.id}) -> /member/detail?id=값
//    @GetMapping("/member/detail2")
//    public String detail2(@RequestParam Long id,Model model){
//        MemberDto member= memberService.memberDetail(id);
//        model.addAttribute("member",member);
//        return "member/detail";
//    }


    @GetMapping("/delete/{id}")  //REST방식(나중),권장
    public String delete(@PathVariable("id")Long id){
        memberService.memberDelete(id);
        return "redirect:/member/memberList"; // 삭제후 Controller -> memberList
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id")Long id,Model model) {
        MemberDto member = memberService.memberDetail(id);
        model.addAttribute("member", member);
        return "member/update";
    }

    @PostMapping("/update")
    public String updateOk(@ModelAttribute MemberDto memberDto){
         memberService.memberUpdate(memberDto);
         return "redirect:/member/detail/"+memberDto.getId();
    }
}
