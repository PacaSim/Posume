package com.SpringClass.posume.service;

import com.SpringClass.posume.dto.*;
import com.SpringClass.posume.entity.*;
import com.SpringClass.posume.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EditService {

    private final MemberRepository memberRepository;
    private final ResumeRepository resumeRepository;
    private final CareerRepository careerRepository;
    private final EducationRepository educationRepository;
    private final SkillRepository skillRepository;
    private final AwardsRepository awardsRepository;
    private final PortfolioRepository portfolioRepository;
    private final ImgService imgService;

    public Skill saveSkill(SkillDto skillDto){
            Optional<Skill> existingSkill = skillRepository.findByMemberId(skillDto.getMember().getId());

            if (existingSkill.isPresent()) {
                // Skill already exists, update it
                Skill skill = existingSkill.get();
                skill.update(skillDto);
                return skill;
        }
            // Skill does not exist, save new Skill
            Skill skill = SkillDto.dtoToEntity(skillDto);
            return skillRepository.save(skill);
    }

    public Resume saveResume(ResumeDto resumeDto){
        Optional<Resume> existingResume  = resumeRepository.findByMemberId(resumeDto.getMember().getId());

        if (existingResume.isPresent()) {
            Resume resume = existingResume.get();
            resume.update(resumeDto);
            return resume;
        }
            Resume resume = ResumeDto.dtoToEntity(resumeDto);
            return resumeRepository.save(resume);
    }

    public Awards saveAwards(AwardsDto awardsDto){
        Optional<Awards> existingAwards = awardsRepository.findByMemberId(awardsDto.getMember().getId());

        if (existingAwards.isPresent()) {
            // Awards already exists, update it
            Awards awards = existingAwards.get();
            awards.update(awardsDto);
            return awards;
        } else {
            // Awards does not exist, save new Awards
            Awards awards = AwardsDto.dtoToEntity(awardsDto);
            return awardsRepository.save(awards);
        }
    }

    public Career saveCareer(CareerDto careerDto) {
        Optional<Career> existingCareer = careerRepository.findByMemberId(careerDto.getMember().getId());

        if (existingCareer.isPresent()) {
            // Career already exists, update it
            Career career = existingCareer.get();
            career.update(careerDto);
            return career;
        } else {
            // Career does not exist, save new Career
            Career career = CareerDto.dtoToEntity(careerDto);
            return careerRepository.save(career);
        }
    }

    public Education saveEducation(EducationDto educationDto) {
        Optional<Education> existingEducation = educationRepository.findByMemberId(educationDto.getMember().getId());

        if (existingEducation.isPresent()) {
            // Education already exists, update it
            Education education = existingEducation.get();
            education.update(educationDto);
            return education;
        } else {
            // Education does not exist, save new Education
            Education education = EducationDto.dtoToEntity(educationDto);
            return educationRepository.save(education);
        }
    }

    public ResumeFormDto getResumeForm(Member member) {
        Optional<Member> user = memberRepository.findByEmail(member.getEmail());
        Optional<Skill> skill = skillRepository.findByMemberId(user.get().getId());
        Optional<Resume> resume = resumeRepository.findByMemberId(user.get().getId());
        Optional<Awards> awards = awardsRepository.findByMemberId(user.get().getId());
        Optional<Career> career = careerRepository.findByMemberId(user.get().getId());
        Optional<Education> education = educationRepository.findByMemberId(user.get().getId());

        SkillDto skillDto = null;
        if (skill.isPresent()) {
            skillDto = SkillDto.entityToDto(skill.get());
        }

        ResumeDto resumeDto = null;
        if (resume.isPresent()) {
            resumeDto = ResumeDto.entityToDto(resume.get());
        }

        AwardsDto awardsDto = null;
        if (awards.isPresent()) {
            awardsDto = AwardsDto.entityToDto(awards.get());
        }

        CareerDto careerDto = null;
        if (career.isPresent()) {
            careerDto = CareerDto.entityToDto(career.get());
        }

        EducationDto educationDto = null;
        if (education.isPresent()) {
            educationDto = EducationDto.entityToDto(education.get());
        }

        ResumeFormDto resumeFormDto = new ResumeFormDto();
        resumeFormDto.setSkillDto(skillDto);
        resumeFormDto.setResumeDto(resumeDto);
        resumeFormDto.setAwardsDto(awardsDto);
        resumeFormDto.setCareerDto(careerDto);
        resumeFormDto.setEducationDto(educationDto);

        return resumeFormDto;
    }


    public Long savePortfolio (@AuthenticationPrincipal UserDetails userDetails, PortfolioDto portfolioDto, List<MultipartFile> projectImgFileList) throws IOException {

        Optional<Member> member = memberRepository.findByEmail(userDetails.getUsername());

        // 상품 등록
        Portfolio portfolio = portfolioDto.creatPortfolio();
        portfolio.setMember(member.get());
        portfolioRepository.save(portfolio);

        // 이미지 등록
        for(int i = 0; i < projectImgFileList.size(); i++){
            Img img = new Img();
            img.setPortfolio(portfolio);
            img.setMember(member.get());
            if(i == 0) {
                img.setRegImgYn("Y");
            } else {
                img.setRegImgYn("N");
            }
            imgService.saveImg(img, projectImgFileList.get(i));
        }
        return portfolio.getId();
    }

}
