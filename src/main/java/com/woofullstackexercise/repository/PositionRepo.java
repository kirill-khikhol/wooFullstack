package com.woofullstackexercise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.PositionEntity;

public interface PositionRepo extends JpaRepository<PositionEntity, Long> {
	@Query("SELECT p FROM PositionEntity p WHERE p NOT IN (SELECT proc.position FROM ProcessEntity proc WHERE proc.candidate = :candidate)")
	List<PositionEntity> findAllNotUsedPositions(@Param("candidate") CandidateEntity candidate);
}
