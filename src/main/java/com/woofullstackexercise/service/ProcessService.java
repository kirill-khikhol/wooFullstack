package com.woofullstackexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woofullstackexercise.dto.ProcessDto;
import com.woofullstackexercise.entities.ProcessEntity;
import com.woofullstackexercise.repository.ProcessRepo;

@Service
public class ProcessService implements IProcessService {
	@Autowired
	ProcessRepo processRepo;
	@Autowired
	IMatchService matchServise;

	@Override
	public ProcessDto acceptProcessStatus(String processId) {
		ProcessEntity processE = processRepo.findById(Long.valueOf(processId)).get();
		String status = processE.getStatus();
		processE.setStatus(STATUS_ACCEPTED);
		if (status.equals(STATUS_NEW)) {
			matchServise.findMatchForCandidate(processE.getCandidate());
		}
		return new ProcessDto(processRepo.save(processE));
	}

	@Override
	public ProcessDto rejectProcessStatus(String processId) {
		ProcessEntity processE = processRepo.findById(Long.valueOf(processId)).get();
		String status = processE.getStatus();
		processE.setStatus(STATUS_REJECTED);
		if (status.equals(STATUS_NEW)) {
			matchServise.findMatchForCandidate(processE.getCandidate());
		}
		return new ProcessDto(processRepo.save(processE));
	}

}
