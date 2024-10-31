package com.sparta.sheduleapp.service;

import com.sparta.sheduleapp.dto.MemberRequestDto;
import com.sparta.sheduleapp.dto.MemberResponseDto;
import com.sparta.sheduleapp.entity.Member;
import com.sparta.sheduleapp.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto getMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + memberId));
        return member.to();
    }

    @Transactional
    public MemberResponseDto createMember(MemberRequestDto memberRequestDto) {
        Member member = Member.from(memberRequestDto);
        Member savedMember = memberRepository.save(member);
        return member.to();
    }

    @Transactional
    public void updateMember(MemberRequestDto memberRequestDto, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + memberId));
        member.updateData(memberRequestDto);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("Todo not found with id: " + memberId));
        memberRepository.deleteById(memberId);
    }
}
