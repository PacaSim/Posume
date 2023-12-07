package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Img;
import lombok.*;
import org.modelmapper.ModelMapper;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImgDto {

    private String imgName;     //이미지 파일명
    private String oriImgName;  //원본 이미지 파일명
    private String imgUrl;      //이미지 파일 경로
    private String regImgYn;    //대표 이미지 여부

    private static ModelMapper modelMapper = new ModelMapper();

    public static ImgDto entityToDto(Img img) {
        return modelMapper.map(img, ImgDto.class);
    }
}
