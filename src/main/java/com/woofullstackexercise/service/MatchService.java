package com.woofullstackexercise.service;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.PositionEntity;
import com.woofullstackexercise.entities.ProcessEntity;
import com.woofullstackexercise.enumirations.Technologies;
import com.woofullstackexercise.repository.CandidateRepo;
import com.woofullstackexercise.repository.PositionRepo;
import com.woofullstackexercise.repository.ProcessRepo;

@Service
public class MatchService implements IMatchService {
	@Autowired
	CandidateRepo candidateRepo;
	@Autowired
	PositionRepo positionRepo;
	@Autowired
	ProcessRepo processRepo;

	private static final int MATCH_CONDITION = 75;
	private static final int MAX_POINTS = 25;
	private static final int MAX_NEW_PROCESSES_FOR_CANDIDATE = 1;

	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private int technologiesExpectationFulfillment = 0;
	private boolean salaryExpectationFulfillment = false;
	private boolean locationExpectationFulfillment = false;

	@Override
	public void findMatchEveryHour() {
		final Runnable matcher = new Runnable() {
			public void run() {
				findMatch();
			}
		};
		final ScheduledFuture<?> matcherHandle = scheduler.scheduleAtFixedRate(matcher, 0, 1, TimeUnit.HOURS);
	}

	private void findMatch() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<      find match     >>>>>>>>>>>>>>>>>>>>>>>>>>");
		Set<CandidateEntity> candSet = candidateRepo.findAllAvaliableCandidates(MAX_NEW_PROCESSES_FOR_CANDIDATE);
		System.out.println("findAllCandidate size: " + candSet.size());
		for (CandidateEntity candidateE : candSet) {
			System.out.println("positionRepo.findAllNotUsedPositions(candidateE): "
					+ positionRepo.findAllNotUsedPositions(candidateE).size());
			findMatchForCandidate(candidateE);
		}
		for (CandidateEntity candidateEntity : candidateRepo
				.findAllAvaliableCandidates(MAX_NEW_PROCESSES_FOR_CANDIDATE)) {
			System.out.println("free candidate: " + candidateEntity.getName());
		}

	}

	@Override
	public void findMatchForCandidate(CandidateEntity candidateE) {
		for (PositionEntity positionE : positionRepo.findAllNotUsedPositions(candidateE)) {

			double res = 0;
			res += matchSkillExp(candidateE, positionE);
			res += matchTechExp(candidateE, positionE);
			res += matchSalaryExp(candidateE, positionE);
			res += matchLocationExp(candidateE, positionE);
			System.out.println("findMatch: " + res);
			if (res >= MATCH_CONDITION) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!      MATCH       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				ProcessEntity processE = new ProcessEntity();
				processE.setCandidate(candidateE);
				processE.setPosition(positionE);
				processE.setStatus(IProcessService.STATUS_NEW);
				processE.setTechnologiesExpectationFulfillment(technologiesExpectationFulfillment);
				processE.setSalaryExpectationFulfillment(salaryExpectationFulfillment);
				processE.setLocationExpectationFulfillment(locationExpectationFulfillment);
				processRepo.save(processE);
				break;
			}
		}
	}

	private double matchLocationExp(CandidateEntity candidateE, PositionEntity positionE) {
		locationExpectationFulfillment = false;
		double res = 0;
		if (candidateE.getExpectation().getLocation()==positionE.getLocation()) {
			res = MAX_POINTS;
			locationExpectationFulfillment = true;
		}
		return res;
	}

	private double matchSalaryExp(CandidateEntity candidateE, PositionEntity positionE) {
		salaryExpectationFulfillment = false;
		double res = (positionE.getSalary().doubleValue() / candidateE.getExpectation().getSalary());

		if (res >= 1) {
			salaryExpectationFulfillment = true;
			res = 1;
		}
		return res * MAX_POINTS;
	}

	private double matchTechExp(CandidateEntity candidateE, PositionEntity positionE) {
		technologiesExpectationFulfillment = 0;
		int count = 0;
		for (Technologies skill : positionE.getTechStack()) {
			if (candidateE.getExpectation().getTechStack().contains(skill)) {
				count++;
			}
		}
		double res = 1. * count / candidateE.getExpectation().getTechStack().size();
		System.out.println("count: " + count + " / candidateE.getExpectation().getTechStack().size(): "
				+ candidateE.getExpectation().getTechStack().size());

		technologiesExpectationFulfillment = count;

		return MAX_POINTS * res;
	}

	private double matchSkillExp(CandidateEntity candidateE, PositionEntity positionE) {
		int count = 0;
		for (Technologies skill : positionE.getTechStack()) {
			if (candidateE.getTechSkills().contains(skill)) {
				count++;
			}
		}
		double res = 0;
		if (count != 0) {
			res = 1. * count / positionE.getTechStack().size();
		}
		return MAX_POINTS * res;
	}

}
