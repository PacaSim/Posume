package com.SpringClass.posume.repository;

import com.SpringClass.posume.entity.Resume;
import com.SpringClass.posume.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SkillRepository extends JpaRepository<Skill, Long> {

    Optional<Skill> findByMemberId(Long memberId);

    Optional<Skill> findById(Long skillId);
}
