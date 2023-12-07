package com.SpringClass.posume.dto;

import com.SpringClass.posume.entity.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberFormDto {

    Long id;

    @NotEmpty(message = "이메일은 필수입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "이름은 필수입력 값입니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수입력 값입니다.")
    @Length(min = 4, max = 16, message = "비밀번호는 4자 이상 16자 이하로 입력해주세요.")
    private String password;

    @NotNull(message = "나이는 필수 입력입니다.")
    private String age;

    @NotBlank(message = "전화번호는 필수 입력입니다.")
    private String phoneNumber;

    @NotBlank(message = "주소는 필수 입력입니다.")
    private String address;

    @NotBlank(message = "학력은 필수 입력입니다.")
    private String academic_background;

    @NotBlank(message = "간단한 자기소개로 어필해주세요.")
    private String introduction;

    private String keyword;

    private List<ImgDto> memberImgDtoList = new ArrayList<>();

    private List<Long> MemberImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Member createMember() {
        return modelMapper.map(this, Member.class);
    }

    public static MemberFormDto entityToDto(Member member) {
        return modelMapper.map(member, MemberFormDto.class);
    }

}
