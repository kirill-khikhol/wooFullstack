package com.woofullstackexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woofullstackexercise.dto.CandidateDto;
import com.woofullstackexercise.repository.CandidateRepo;

@Service
public class CandidateService implements ICandidateService {
	@Autowired
	CandidateRepo candidateRepo;

	@Override
	public CandidateDto findById(String name) {
		return new CandidateDto(candidateRepo.findById(name).get());
	}

}
