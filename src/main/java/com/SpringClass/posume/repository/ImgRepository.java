package com.SpringClass.posume.repository;

import com.SpringClass.posume.entity.Img;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImgRepository extends JpaRepository<Img, Long> {
    List<Img> findAllByMemberId(Long memberId);

    List<Img> findByPortfolioId(Long portfolioId);
}
