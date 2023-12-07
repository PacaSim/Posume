package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Member;
import com.SpringClass.posume.entity.Resume;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDto {

    private Long id;
    private Member member;
    private String facebook;
    private String instagram;
    private String github;

    private static ModelMapper modelMapper = new ModelMapper();

    public static Resume dtoToEntity(ResumeDto resumeDto) {
        return modelMapper.map(resumeDto, Resume.class);
    }
    public static ResumeDto entityToDto(Resume resume) {
        return modelMapper.map(resume, ResumeDto.class);
    }


}
