package com.SpringClass.posume.repository;

import com.SpringClass.posume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByMemberId(Long memberId);

    Optional<Resume> findById(Long resumeId);
}
