package com.SpringClass.posume.entity;

import com.SpringClass.posume.dto.ResumeDto;
import com.SpringClass.posume.dto.SkillDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @Column(name="skill_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String skill1;

    private String skill2;

    private String skill3;

    private String skill4;

    private int per1;

    private int per2;

    private int per3;

    private int per4;

    public void update(SkillDto skillDto) {
        this.skill1 = skillDto.getSkill1();
        this.skill2 = skillDto.getSkill2();
        this.skill3 = skillDto.getSkill3();
        this.skill4 = skillDto.getSkill4();
        this.per1 = skillDto.getPer1();
        this.per2 = skillDto.getPer2();
        this.per3 = skillDto.getPer3();
        this.per4 = skillDto.getPer4();
    }
}
