package org.spring.springmvc2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springmvc2.constraint.Role;
import org.spring.springmvc2.dto.MemberDto;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name="member_test_tb2")
public class MemberEntity {

    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto_increment
    @Column(name="member_id")
    private Long id;


    //null 허용 -> false , 유니크 -> true (not null, unique)
    @Column(nullable = false,unique = true)
    private String userEmail;

    @Column(nullable = false)
    private String userPw;
    //글자수 제한(길이 100까지)
    @Column(nullable = false,length = 100)
    private String userName;

    @Column(nullable = false,length = 100)
    private String phone;

    @Column(nullable = false,length = 100)
    private String addr;

    @Enumerated(EnumType.STRING) //Enum 타입이 문자열임
    private Role role;

    //날짜시간 필수
    @CreationTimestamp // 처음 생성시 현재시간 저장
    @Column(updatable = false) // 업데이트시 저장 X -> 처음 생성시 한번만 저장됨
    private LocalDateTime createTime;

    @UpdateTimestamp // 수정 시 마다 현재시간 저장
    @Column(insertable = false) // 처음 생성 시 저장X -> 업데이트 시에만 저장됨
    private LocalDateTime updateTime;


    //DTO -> Entity

    public static MemberEntity toInsertMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setUserName(memberDto.getUserName());
        memberEntity.setPhone(memberDto.getPhone());
        memberEntity.setAddr(memberDto.getAddr());
        memberEntity.setRole(Role.MEMBER);
        memberEntity.setCreateTime(memberDto.getCreateTime());

        return memberEntity;
    }
    //회원 수정
    public static MemberEntity toUpdateMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(memberDto.getId()); // 수정 시 아이디 필수
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setUserName(memberDto.getUserName());
        memberEntity.setPhone(memberDto.getPhone());
        memberEntity.setAddr(memberDto.getAddr());
        memberEntity.setRole(memberDto.getRole());
        //수정 시간은 자동으로 설정
        memberEntity.setCreateTime(memberDto.getCreateTime());
        memberEntity.setUpdateTime(memberDto.getUpdateTime());
        return memberEntity;
    }



}
