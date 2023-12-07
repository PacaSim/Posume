package com.SpringClass.posume.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Img {

    @Id
    @Column(name="img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imgName; //이미지 파일명
    private String oriImgName; //원본 이미지 파일명
    private String imgUrl;  //이미지 파일 경로
    private String regImgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    private Portfolio portfolio;

    public void updateImg(String imgName, String oriImgName, String imgUrl) {
        this.imgName = imgName;
        this.oriImgName = oriImgName;
        this.imgUrl = imgUrl;
    }

}
