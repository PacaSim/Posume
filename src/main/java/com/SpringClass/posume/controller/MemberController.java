package com.SpringClass.posume.controller;

import com.SpringClass.posume.dto.MemberFormDto;
import com.SpringClass.posume.entity.Member;
import com.SpringClass.posume.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Binding;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/member/new")
    public String memberForm(Model model) {

        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

    @PostMapping("/member/new")
    public String insertMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult,
                               Model model, @RequestParam("memberImgFile")List<MultipartFile> memberImgFileList) {

        if (bindingResult.hasErrors()) {
            return "member/memberForm";
        }

        if(memberImgFileList.get(0).isEmpty() && memberFormDto.getId() == null){
            model.addAttribute("errorMessage", "이력서 사진은 필수입니다.");
            return "member/memberForm";
        }

        try{
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
            memberService.saveImg(member, memberImgFileList);

        } catch(Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }

        return "redirect:/";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "member/memberLoginForm";
    }

    @GetMapping("/member/logout")
    public String logoutForm(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }


    @GetMapping("/member/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호가 일치하지 않습니다.");
        return "member/memberLoginForm";
    }
}
