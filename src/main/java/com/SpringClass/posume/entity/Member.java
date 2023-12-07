package com.SpringClass.posume.entity;

import com.SpringClass.posume.constant.Role;
import com.SpringClass.posume.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    @Column(nullable = false)
    private String password;

    private String age;

    private String phoneNumber;

    private String address;

    private String academic_background;

    private String introduction;

    private String keyword;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = Member.builder()
                .role(Role.USER)
                .email(memberFormDto.getEmail())
                .address(memberFormDto.getAddress())
                .name(memberFormDto.getName())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .age(memberFormDto.getAge())
                .phoneNumber(memberFormDto.getPhoneNumber())
                .academic_background(memberFormDto.getAcademic_background())
                .introduction(memberFormDto.getIntroduction())
                .keyword(memberFormDto.getKeyword())
                .build();

        return member;
    }

}
