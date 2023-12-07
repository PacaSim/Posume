package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Member;
import com.SpringClass.posume.entity.Portfolio;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioDto {

    private Long id;

    Member member;

    @NotBlank(message = "프로젝트명은 필수 입력 값입니다.")
    private String title;

    private String category;

    private String detail;

    private List<String> imgUrls;

    private List<ImgDto> PortImgDtoList = new ArrayList<>();

    private List<Long> PortImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Portfolio creatPortfolio() {
        return modelMapper.map(this, Portfolio.class);
    }

    public static PortfolioDto entityToDto(Portfolio portfolio) {
        PortfolioDto portfolioDto = modelMapper.map(portfolio, PortfolioDto.class);
        return portfolioDto;
    }
}
