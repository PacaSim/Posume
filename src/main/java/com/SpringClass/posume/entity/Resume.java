package com.SpringClass.posume.entity;

import com.SpringClass.posume.dto.ResumeDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Resume {

    @Id
    @Column(name="resume_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    private String facebook;

    private String instagram;

    private String github;

    public void update(ResumeDto resumeDto) {
        this.facebook = resumeDto.getFacebook();
        this.instagram = resumeDto.getInstagram();
        this.github = resumeDto.getGithub();
    }
}
