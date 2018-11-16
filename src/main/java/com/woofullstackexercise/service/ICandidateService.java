package com.woofullstackexercise.service;

import com.woofullstackexercise.dto.CandidateDto;
import com.woofullstackexercise.enumirations.StatusEnum;

public interface ICandidateService {
	CandidateDto findById(String name);

	CandidateDto changeProcessStatus(String processId, StatusEnum status);
}
