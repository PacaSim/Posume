package com.SpringClass.posume.entity;

import com.SpringClass.posume.constant.Role;
import com.SpringClass.posume.dto.AwardsDto;
import com.SpringClass.posume.dto.CareerDto;
import com.SpringClass.posume.dto.MemberFormDto;
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
public class Career {

    @Id
    @Column(name="career_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    private String companyName;

    private String position;

    private String detail;

    private String startYear;

    private String endYear;

    public void update(CareerDto careerDto) {
        this.companyName = careerDto.getCompanyName();
        this.position = careerDto.getPosition();
        this.detail = careerDto.getDetail();
        this.startYear = careerDto.getStartYear();
        this.endYear = careerDto.getEndYear();

    }
}
