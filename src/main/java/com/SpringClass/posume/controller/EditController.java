package com.SpringClass.posume.controller;

import com.SpringClass.posume.dto.*;
import com.SpringClass.posume.entity.*;
import com.SpringClass.posume.repository.*;
import com.SpringClass.posume.service.EditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EditController {

    private final EditService editService;
    private final MemberRepository memberRepository;


    @GetMapping(value = "/edit/resume")
    public String resumeForm(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        Optional<Member> member = memberRepository.findByEmail(userDetails.getUsername());
        Member validMember = member.orElseThrow(() -> new NoSuchElementException("Member not found for email: " + userDetails.getUsername()));
        ResumeFormDto resumeForm = editService.getResumeForm(validMember);

        model.addAttribute("resumeFormDto", resumeForm);
        return "/edit/editResumeForm";
    }

    @PostMapping("/edit/resume")
    public String registerResume(@AuthenticationPrincipal UserDetails userDetails, ResumeFormDto resumeFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/edit/editResumeForm";
        }
        Optional<Member> member = memberRepository.findByEmail(userDetails.getUsername());

        try{
            ResumeDto resumeDto = resumeFormDto.getResumeDto();
            AwardsDto awardsDto = resumeFormDto.getAwardsDto();
            CareerDto careerDto = resumeFormDto.getCareerDto();
            EducationDto educationDto = resumeFormDto.getEducationDto();
            SkillDto skillDto = resumeFormDto.getSkillDto();

            skillDto.setMember(member.get());
            resumeDto.setMember(member.get());
            awardsDto.setMember(member.get());
            careerDto.setMember(member.get());
            educationDto.setMember(member.get());

            editService.saveResume(resumeDto);
            editService.saveCareer(careerDto);
            editService.saveEducation(educationDto);
            editService.saveSkill(skillDto);
            editService.saveAwards(awardsDto);

        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "/edit/editResumeForm";
        }
        return "redirect:/";
    }

    @GetMapping(value = "/edit/portfolio")
    public String editPortfolio(Model model) {

        model.addAttribute("portfolioDto", new PortfolioDto());
        return "/edit/editPortfolioForm";
    }

    @PostMapping("/edit/portfolio")
    public String registerPortfolio(@AuthenticationPrincipal UserDetails userDetails,PortfolioDto portfolioDto, BindingResult bindingResult,
                           Model model, @RequestParam("projectImgFile") List<MultipartFile> projectImgFileList){
        Optional<Member> member = memberRepository.findByEmail(userDetails.getUsername());
        if (bindingResult.hasErrors()) {
            return "/edit/editPortfolioForm";
        }

        if (projectImgFileList.get(0).isEmpty() && portfolioDto.getId() == null) {
            model.addAttribute("errorMessage", "첫 번째 이미지는 필수 입니다.");
            return "/edit/editPortfolioForm";
        }
        try {
            editService.savePortfolio(userDetails, portfolioDto, projectImgFileList);
        } catch(IOException e) {
            model.addAttribute("errorMessage", "파일 저장에 실패했습니다.");
            return "/edit/editPortfolioForm";
        }
        return "redirect:/";
    }
}
