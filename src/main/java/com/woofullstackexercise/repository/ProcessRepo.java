package com.woofullstackexercise.repository;

import java.util.HashSet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.ProcessEntity;

public interface ProcessRepo extends JpaRepository<ProcessEntity, Long> {
	@Query("SELECT p.candidate FROM ProcessEntity p WHERE p.status IS NOT 'New'")
	HashSet<CandidateEntity> findAllCandidate();
}
