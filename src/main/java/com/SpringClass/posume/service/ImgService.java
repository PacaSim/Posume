package com.SpringClass.posume.service;

import com.SpringClass.posume.entity.Img;
import com.SpringClass.posume.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class ImgService {

    @Value("${imgLocation}")
    private String imgLocation;

    private final ImgRepository imgRepository;
    private final FileService fileService;

    public void saveImg(Img img, MultipartFile imgFile) throws IOException {
        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(imgLocation, oriImgName, imgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        img.updateImg(oriImgName, imgName, imgUrl);
        imgRepository.save(img);
    }
}
