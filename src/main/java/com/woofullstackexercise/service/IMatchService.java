package com.woofullstackexercise.service;

import com.woofullstackexercise.entities.CandidateEntity;

public interface IMatchService {

	CandidateEntity findMatchForCandidate(CandidateEntity candidate);

	void findMatchEveryHour();
}
