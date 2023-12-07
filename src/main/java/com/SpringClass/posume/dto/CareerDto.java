package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Career;
import com.SpringClass.posume.entity.Member;
import com.SpringClass.posume.entity.Resume;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CareerDto {

    private Long id;

    Member member;

    private String companyName;

    private String position;

    private String detail;

    private String startYear;

    private String endYear;

    private static ModelMapper modelMapper = new ModelMapper();

    public static Career dtoToEntity(CareerDto careerDto) {
        return modelMapper.map(careerDto, Career.class);
    }

    public static CareerDto entityToDto(Career career) {
        return modelMapper.map(career, CareerDto.class);
    }
}
