package com.SpringClass.posume.repository;

import com.SpringClass.posume.entity.Education;
import com.SpringClass.posume.entity.Resume;
import com.SpringClass.posume.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationRepository extends JpaRepository<Education, Long> {

    Optional<Education> findById(Long educationId);
    Optional<Education> findByMemberId(Long memberId);
}
