package com.SpringClass.posume.service;

import com.SpringClass.posume.dto.MemberFormDto;
import com.SpringClass.posume.entity.Img;
import com.SpringClass.posume.entity.Member;
import com.SpringClass.posume.repository.ImgRepository;
import com.SpringClass.posume.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final ImgService imgService;
    private final ImgRepository imgRepository;

    public Long saveImg(Member member, List<MultipartFile> imgFileList) throws IOException {
        for (int i=0; i<imgFileList.size(); i++) {
            Img img = new Img();
            img.setMember(member);
            if (i==0) {
                img.setRegImgYn("Y");
            } else {
                img.setRegImgYn("N");
            }
            imgService.saveImg(img, imgFileList.get(i));
        }
        return member.getId();
    }

    public Member saveMember(Member member) {
        validateDuplicationMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicationMember(Member member) {
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember.isPresent()) {
            System.out.println(findMember.get().getName());
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("해당 사용자가 없습니다." + email));

        log.info("==================로그인 사용자 : "+member);

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
