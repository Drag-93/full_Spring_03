package org.spring.springmvc2.service.impl;

import lombok.RequiredArgsConstructor;
import org.spring.springmvc2.dto.MemberDto;
import org.spring.springmvc2.entity.MemberEntity;
import org.spring.springmvc2.repository.MemberRepository;
import org.spring.springmvc2.service.MemberService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemverServiceImpl implements MemberService {

    private final MemberRepository memberRepository;


    //이메일체크
    @Override
    public MemberDto emailCheck(String userEmail) {
        Optional<MemberEntity> optionalMemberEntity= memberRepository.findByUserEmail(userEmail);

        if(!optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 이메일");
        }

        return MemberDto.toMemberDto(optionalMemberEntity.get());

    }
    //회원가입
    @Override
    public void memberInsert(MemberDto memberDto) {
        Optional<MemberEntity> optionalMemberEntity=memberRepository.findByUserEmail(memberDto.getUserEmail());

        if(optionalMemberEntity.isPresent()){
            throw  new IllegalArgumentException("이미 존재하는 이메일");
        }
        memberRepository.save(MemberEntity.toInsertMemberEntity(memberDto));
    }

    //회원수정 ->
    @Override
    public void memberUpdate(MemberDto memberDto) {
        Optional<MemberEntity> optionalMemberEntity=memberRepository.findById(memberDto.getId());
        if(!optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 회원");
        }
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDto));

    }
    //회원삭제
    @Override
    public void memberDelete(Long id) {
        Optional<MemberEntity> optionalMemberEntity=memberRepository.findById(id);
        if(!optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 회원");
        }
        memberRepository.deleteById(id);
    }
    //회원목록
    @Override
    public List<MemberDto> memberList() {
        List<MemberDto> memberDtos = new ArrayList<MemberDto>();
        List<MemberEntity> memberEntities = memberRepository.findAll();

        for(MemberEntity memberEntity:memberEntities){
            MemberDto memberDto=MemberDto.toMemberDto(memberEntity);
            memberDtos.add(memberDto);
        }
        return memberDtos;
    }
    //회원상세조회
    @Override
    public MemberDto memberDetail(Long id) {
        Optional<MemberEntity> optionalMemberEntity=memberRepository.findById(id);
        if(!optionalMemberEntity.isPresent()){
            throw new IllegalArgumentException("존재하지 않는 회원");
        }
        return MemberDto.toMemberDto(optionalMemberEntity.get());
    }
}
