package com.woofullstackexercise.repository;

import java.util.HashSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woofullstackexercise.entities.CandidateEntity;

public interface CandidateRepo extends JpaRepository<CandidateEntity, String> {

	@Query("SELECT c FROM CandidateEntity c WHERE c.newCount < :m")
	HashSet<CandidateEntity> findAllAvaliableCandidates(@Param("m") int maxNewProcessesForCandidate);

}
