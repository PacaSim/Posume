package com.SpringClass.posume.controller;

import com.SpringClass.posume.dto.*;
import com.SpringClass.posume.entity.*;
import com.SpringClass.posume.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final MemberRepository memberRepository;
    private final ResumeRepository resumeRepository;
    private final PasswordEncoder passwordEncoder;
    private final ImgRepository imgRepository;
    private final SkillRepository skillRepository;
    private final EducationRepository educationRepository;
    private final CareerRepository careerRepository;
    private final AwardsRepository awardsRepository;
    private final PortfolioRepository portfolioRepository;

    @GetMapping(value = "/")
    public String index (@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            Optional<Member> member = memberRepository.findByEmail(userDetails.getUsername());
            List<Img> imgs = imgRepository.findAllByMemberId(member.get().getId());
            Optional<Resume> resume = resumeRepository.findByMemberId(member.get().getId());
            Optional<Skill> skill = skillRepository.findByMemberId(member.get().getId());
            Optional<Education> education = educationRepository.findByMemberId(member.get().getId());
            Optional<Career> career = careerRepository.findByMemberId(member.get().getId());
            Optional<Awards> awards = awardsRepository.findByMemberId(member.get().getId());
            List<Portfolio> portfolio = portfolioRepository.findAllByMemberId(member.get().getId());
            MemberFormDto memberFormDto = MemberFormDto.builder()
                    .email(member.get().getEmail())
                    .address(member.get().getAddress())
                    .name(member.get().getName())
                    .password(passwordEncoder.encode(member.get().getPassword()))
                    .age(member.get().getAge())
                    .phoneNumber(member.get().getPhoneNumber())
                    .academic_background(member.get().getAcademic_background())
                    .introduction(member.get().getIntroduction())
                    .keyword(member.get().getKeyword())
                    .build();
            model.addAttribute("MemberFormDto", memberFormDto);
            if(!imgs.isEmpty()){
                model.addAttribute("imgurl", imgs.get(0).getImgUrl());
            }
        if (resume.isPresent()) {
            ResumeDto resumeDto = ResumeDto.builder()
                    .github(resume.get().getGithub())
                    .facebook(resume.get().getFacebook())
                    .instagram(resume.get().getInstagram())
                    .build();
            model.addAttribute("resumeDto", resumeDto);
        }
        if (skill.isPresent()) {
            SkillDto skillDto = SkillDto.builder()
                    .skill1(skill.get().getSkill1())
                    .per1(skill.get().getPer1())
                    .skill2(skill.get().getSkill2())
                    .per2(skill.get().getPer2())
                    .skill3(skill.get().getSkill3())
                    .per3(skill.get().getPer3())
                    .skill4(skill.get().getSkill4())
                    .per4(skill.get().getPer4())
                    .build();
            model.addAttribute("SkillDto", skillDto);
        }
        if (education.isPresent()) {
            EducationDto educationDto = EducationDto.builder()
                    .highName(education.get().getHighName())
                    .highStart(education.get().getHighStart())
                    .highEnd(education.get().getHighEnd())
                    .uniName(education.get().getUniName())
                    .uniStart(education.get().getUniStart())
                    .uniEnd(education.get().getUniEnd())
                    .gradName(education.get().getGradName())
                    .gradStart(education.get().getGradStart())
                    .gradEnd(education.get().getGradEnd())
                    .build();
            model.addAttribute("educationDto", educationDto);
        }

        if(awards.isPresent()) {
            AwardsDto awardsDto = AwardsDto.builder()
                    .awardName1(awards.get().getAwardName1())
                    .awardName2(awards.get().getAwardName2())
                    .awardName3(awards.get().getAwardName3())
                    .awardName4(awards.get().getAwardName4())
                    .build();
            model.addAttribute("awardsDto",awardsDto);
        }
        if(career.isPresent()) {
            CareerDto careerDto = CareerDto.builder()
                    .companyName(career.get().getCompanyName())
                    .startYear(career.get().getStartYear())
                    .endYear(career.get().getEndYear())
                    .position(career.get().getPosition())
                    .detail(career.get().getDetail())
                    .build();
            model.addAttribute("careerDto", careerDto);
        }
            if (!portfolio.isEmpty()) {
                List<PortfolioDto> portfolioDtos = new ArrayList<>();
                for (Portfolio p : portfolio) {
                    PortfolioDto portfolioDto = PortfolioDto.builder()
                            .title(p.getTitle())
                            .category(p.getCategory())
                            .detail(p.getDetail())
                            .build();
                    portfolioDto.setMember(member.get());
                    portfolioDtos.add(portfolioDto);
                }
                model.addAttribute("portfolioDtos", portfolioDtos);
                for(int i=1; i<4; i++){
                        model.addAttribute("imgurl" + i, imgs.get(i).getImgUrl());

                }
            }



        }



        return "/index";
    }


}
