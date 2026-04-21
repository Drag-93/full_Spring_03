package org.spring.springmvc2;

import org.junit.jupiter.api.Test;
import org.spring.springmvc2.dto.MemberDto;
import org.spring.springmvc2.entity.MemberEntity;
import org.spring.springmvc2.repository.MemberRepository;
import org.spring.springmvc2.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class MemberTest {
    @Autowired
    MemberRepository memberRepository;

    @Autowired
    MemberService service;

    @Test
    void insert(){
        MemberDto memberDto = new MemberDto();

        memberDto.setUserEmail("m6@email");
        memberDto.setUserPw("11");
        memberDto.setUserName("m6");
        memberDto.setPhone("01066666666");
        memberDto.setAddr("경기");

        service.memberInsert(memberDto);

    }
    @Test
    void update(){
        Optional<MemberEntity> optionalMemberEntity=memberRepository.findById(6L);
        MemberDto memberDto = MemberDto.toMemberDto(optionalMemberEntity.get());

        memberDto.setUserName("tester");
        memberDto.setUserEmail("test@email");

        service.memberUpdate(memberDto);

    }

    @Test
    void memberList(){
        System.out.println(service.memberList());

    }

    @Test
    void detail(){
        System.out.println(service.memberDetail(6L));
    }

    @Test
    void email(){
        System.out.println(service.emailCheck("tst@email"));
    }

    @Test
    void delete(){
        service.memberDelete(6L);
    }
}
