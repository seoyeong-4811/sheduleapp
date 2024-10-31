package com.sparta.sheduleapp.entity;

import com.sparta.sheduleapp.dto.AuthRequestDto;
import com.sparta.sheduleapp.dto.MemberRequestDto;
import com.sparta.sheduleapp.dto.MemberResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class Member extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String memberName;

    @Column
    private String email;

    @Column
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @ManyToMany(mappedBy = "memberList")
    private List<Todo> todoList = new ArrayList<>();

    public static Member from(MemberRequestDto requestDto) {
        Member member = new Member();
        member.initData(requestDto);
        return member;
    }

    private void initData(MemberRequestDto requestDto) {
        this.memberName = requestDto.getMemberName();
        this.email = requestDto.getEmail();
    }

    public static Member from(AuthRequestDto requestDto, UserRoleEnum role) {
        Member member = new Member();
        member.initData(requestDto, role);
        return member;
    }

    private void initData(AuthRequestDto requestDto, UserRoleEnum role) {
        this.memberName = requestDto.getMemberName();
        this.email = requestDto.getEmail();
        this.password = requestDto.getPassword();
        this.role = role;
    }

    public MemberResponseDto to() {
        return new MemberResponseDto(
                this.id,
                this.memberName,
                this.email
        );
    }

    public void updateData(MemberRequestDto memberRequestDto) {
        this.memberName = memberRequestDto.getMemberName();
        this.email = memberRequestDto.getEmail();
    }
    public boolean isUser() {
        return this.role == UserRoleEnum.USER;
    }
}