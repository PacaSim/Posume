package com.SpringClass.posume.repository;

import com.SpringClass.posume.entity.Career;
import com.SpringClass.posume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<Career> findByMemberId(Long memberId);

    Optional<Career> findById(Long careerId);
}
