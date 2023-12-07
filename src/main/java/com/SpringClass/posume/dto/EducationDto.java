package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Education;
import com.SpringClass.posume.entity.Member;
import com.SpringClass.posume.entity.Resume;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationDto {

    private Long id;

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

    private static ModelMapper modelMapper = new ModelMapper();

    public static Education dtoToEntity(EducationDto educationDto) {
        return modelMapper.map(educationDto, Education.class);
    }

    public static EducationDto entityToDto(Education education) {
        return modelMapper.map(education, EducationDto.class);
    }
}
