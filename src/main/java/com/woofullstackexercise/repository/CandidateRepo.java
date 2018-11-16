package com.woofullstackexercise.repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.woofullstackexercise.entities.CandidateEntity;

public interface CandidateRepo extends JpaRepository<CandidateEntity, String> {
	@Query("SELECT c FROM CandidateEntity c WHERE c.name IS NOT NULL")
	HashSet<CandidateEntity> findAllCandidate();
//	@Query("Select c from CandidateEntity c JOIN c.techStack t WHERE t.techName IN :techs")
//	HashSet<CandidateEntity> getAllByTech(@Param("techs") List<String> techName);

	@Query("SELECT c FROM CandidateEntity c WHERE c NOT IN (SELECT p.candidate FROM ProcessEntity p WHERE p.status = 'New')")
	HashSet<CandidateEntity> findAllAvaliableCandidates(int maxNewProcessesForCandidate);
//	@Query("SELECT c FROM CandidateEntity c WHERE c NOT IN (SELECT COUNT(p.candidate) FROM ProcessEntity p WHERE p.status = 'New' GROUP BY p.candidate)")
//	HashSet<CandidateEntity> findAllAvaliableCandidates(int maxNewProcessesForCandidate);

//	amountOfNewProcessesForCandidate
}
