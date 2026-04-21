package org.spring.springmvc2.dto;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springmvc2.constraint.Role;
import org.spring.springmvc2.entity.MemberEntity;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class MemberDto {

    private Long id;

    private String userEmail;

    private String userPw;

    private String userName;

    private String phone;

    private String addr;

    private Role role;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;


//Entity -> DTO

    public static MemberDto toMemberDto(MemberEntity memberEntity) {
        MemberDto memberDto = new MemberDto();
        memberDto.setId(memberEntity.getId());
        memberDto.setUserEmail(memberEntity.getUserEmail());
        memberDto.setUserPw(memberEntity.getUserPw());
        memberDto.setUserName(memberEntity.getUserName());
        memberDto.setPhone(memberEntity.getPhone());
        memberDto.setAddr(memberEntity.getAddr());
        memberDto.setRole(memberEntity.getRole());
        memberDto.setCreateTime(memberEntity.getCreateTime());
        memberDto.setUpdateTime(memberEntity.getUpdateTime());
        return memberDto;
    }
}
