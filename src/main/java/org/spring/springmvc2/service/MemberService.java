package org.spring.springmvc2.service;

import org.spring.springmvc2.dto.MemberDto;

import java.util.List;

public interface MemberService {

    MemberDto emailCheck(String userEmail);

    void memberInsert(MemberDto memberDto);

    void memberUpdate(MemberDto memberDto);

    void memberDelete(Long id);

    List<MemberDto> memberList();

    MemberDto memberDetail(Long id);

    MemberDto memberLogin(MemberDto memberDto);
}
