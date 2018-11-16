package com.woofullstackexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woofullstackexercise.dto.CandidateDto;
import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.ProcessEntity;
import com.woofullstackexercise.enumirations.StatusEnum;
import com.woofullstackexercise.repository.CandidateRepo;
import com.woofullstackexercise.repository.ProcessRepo;

@Service
public class CandidateService implements ICandidateService {
	@Autowired
	CandidateRepo candidateRepo;
	@Autowired
	ProcessRepo processRepo;
	@Autowired
	IMatchService matchServise;

	@Override
	public CandidateDto findById(String name) {
		return new CandidateDto(candidateRepo.findById(name).get());
	}


	@Override
	public CandidateDto changeProcessStatus(String processId, StatusEnum status) {
		ProcessEntity processE = processRepo.findById(Long.valueOf(processId)).get();
		CandidateEntity candidateE = processE.getCandidate();
		candidateE.setNewCount(candidateE.getNewCount() - 1);
		if (status == StatusEnum.ACCEPTED) {
			processE.setStatus(StatusEnum.ACCEPTED);
			candidateE.setAcceptedCount(candidateE.getAcceptedCount() + 1);
		} else if (status == StatusEnum.REJECTED) {
			processE.setStatus(StatusEnum.REJECTED);
			candidateE.setRejectedCount(candidateE.getRejectedCount() + 1);
		}

		return new CandidateDto(matchServise.findMatchForCandidate(processE.getCandidate()));
	}

}
