package com.sparta.sheduleapp.repository;

import com.sparta.sheduleapp.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
