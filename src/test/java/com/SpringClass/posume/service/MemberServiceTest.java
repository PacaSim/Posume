package com.SpringClass.posume.service;

import com.SpringClass.posume.dto.MemberFormDto;
import com.SpringClass.posume.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberService memberService;

    @Test
    void saveMemberTest() {
        Member member = createMember();
        System.out.println(member);
        Member member1 = memberService.saveMember(member);
        System.out.println(member1);
    }

    @Test
    @DisplayName("duplication member test")
    void saveMemberTest2() {
        Member member1 = createMember();
        Member member2 = createMember();
        memberService.saveMember(member1);

        Throwable e = assertThrows(IllegalStateException.class, () -> {
            memberService.saveMember(member2);});

        assertEquals("이미 존재하는 회원입니다.", e.getMessage());
    }

    public Member createMember() {
        MemberFormDto dto = MemberFormDto.builder()
                .address("인천시 미추홀구")
                .email("test@test.com")
                .password("1111")
                .name("홍길동")
                .build();

        Member member = Member.createMember(dto, passwordEncoder);
        return member;
    }
}