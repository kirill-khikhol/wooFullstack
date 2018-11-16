package com.woofullstackexercise.service;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.ProcessEntity;

public interface IMatchService {

	void findMatchForCandidate(CandidateEntity candidate);

	void findMatchEveryHour();
}
