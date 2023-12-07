package com.SpringClass.posume.entity;

import com.SpringClass.posume.constant.Role;
import com.SpringClass.posume.dto.EducationDto;
import com.SpringClass.posume.dto.MemberFormDto;
import com.SpringClass.posume.dto.SkillDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Education {

    @Id
    @Column(name="education_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    private String highName;

    private String highStart;

    private String highEnd;

    private String uniName;

    private String uniStart;

    private String uniEnd;

    private String gradName;

    private String gradStart;

    private String gradEnd;

    public void update(EducationDto educationDto) {
        this.highName = educationDto.getHighName();
        this.highStart = educationDto.getHighStart();
        this.highEnd = educationDto.getHighEnd();
        this.uniName = educationDto.getUniName();
        this.uniStart = educationDto.getUniStart();
        this.uniEnd = educationDto.getUniEnd();
        this.gradName = educationDto.getGradName();
        this.gradStart = educationDto.getGradStart();
        this.gradEnd = educationDto.getGradEnd();
    }

}
