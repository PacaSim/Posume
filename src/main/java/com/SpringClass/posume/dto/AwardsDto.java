package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Awards;
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
public class AwardsDto {

    private Long id;

    Member member;

    private String awardName1;
    private String awardName2;
    private String awardName3;
    private String awardName4;

    private static ModelMapper modelMapper = new ModelMapper();

    public static Awards dtoToEntity(AwardsDto awardsDto) {
        return modelMapper.map(awardsDto, Awards.class);
    }

    public static AwardsDto entityToDto(Awards awards) {
        return modelMapper.map(awards, AwardsDto.class);
    }
}
