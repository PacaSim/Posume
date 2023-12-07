package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Career;
import com.SpringClass.posume.entity.Member;
import com.SpringClass.posume.entity.Resume;
import com.SpringClass.posume.entity.Skill;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SkillDto {

    private Long id;

    private Member member;

    private String skill1;

    private String skill2;

    private String skill3;

    private String skill4;

    private int per1;

    private int per2;

    private int per3;

    private int per4;

    private static ModelMapper modelMapper = new ModelMapper();

    public static Skill dtoToEntity(SkillDto skillDto) {
        return modelMapper.map(skillDto, Skill.class);
    }

    public static SkillDto entityToDto(Skill skill) {
        return modelMapper.map(skill, SkillDto.class);
    }
}
