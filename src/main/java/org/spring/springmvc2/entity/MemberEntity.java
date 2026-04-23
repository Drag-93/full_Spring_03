package org.spring.springmvc2.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.spring.springmvc2.common.BasicTime;
import org.spring.springmvc2.common.Role;
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
public class MemberEntity extends BasicTime {

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




    //DTO -> Entity

    public static MemberEntity toInsertMemberEntity(MemberDto memberDto){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setUserEmail(memberDto.getUserEmail());
        memberEntity.setUserPw(memberDto.getUserPw());
        memberEntity.setUserName(memberDto.getUserName());
        memberEntity.setPhone(memberDto.getPhone());
        memberEntity.setAddr(memberDto.getAddr());
        memberEntity.setRole(Role.MEMBER);

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

        return memberEntity;
    }



}
