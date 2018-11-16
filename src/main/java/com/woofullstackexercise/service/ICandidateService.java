package com.woofullstackexercise.service;

import com.woofullstackexercise.dto.CandidateDto;

public interface ICandidateService {
	CandidateDto findById(String name);
}
