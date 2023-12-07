package com.SpringClass.posume.entity;

import com.SpringClass.posume.constant.Role;
import com.SpringClass.posume.dto.AwardsDto;
import com.SpringClass.posume.dto.EducationDto;
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
public class Awards {

    @Id
    @Column(name="awards_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    private String awardName1;
    private String awardName2;
    private String awardName3;
    private String awardName4;

    public void update(AwardsDto awardsDto) {
        this.awardName1 = awardsDto.getAwardName1();
        this.awardName2 = awardsDto.getAwardName2();
        this.awardName3 = awardsDto.getAwardName3();
        this.awardName4 = awardsDto.getAwardName4();

    }
}
