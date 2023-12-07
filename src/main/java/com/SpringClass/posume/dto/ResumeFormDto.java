package com.SpringClass.posume.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeFormDto {

    ResumeDto resumeDto;

    AwardsDto awardsDto;

    CareerDto careerDto;

    SkillDto skillDto;

    EducationDto educationDto;

}
