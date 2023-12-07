package com.SpringClass.posume.repository;

import com.SpringClass.posume.entity.Awards;
import com.SpringClass.posume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AwardsRepository extends JpaRepository<Awards, Long> {

    Optional<Awards> findByMemberId(Long memberId);

    Optional<Awards> findById(Long awardsId);
}
