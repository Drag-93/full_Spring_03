package org.spring.springmvc2.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.spring.springmvc2.common.Role;
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
    @NotBlank(message = "이메일을 입력해주세요")
    @Size(min=3)
    private String userEmail;
    @NotBlank(message = "비밀번호를 입력해주세요")
    @Size(min=3)
    private String userPw;
    @NotBlank(message = "이름을 입력해주세요")
    @Size(min=3, max = 20)
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
