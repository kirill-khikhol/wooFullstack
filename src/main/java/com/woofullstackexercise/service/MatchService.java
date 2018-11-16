package com.woofullstackexercise.service;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.PositionEntity;
import com.woofullstackexercise.entities.ProcessEntity;
import com.woofullstackexercise.enumirations.StatusEnum;
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

	private int technologiesExpectationFulfillment = 0;
	private boolean salaryExpectationFulfillment = false;
	private boolean locationExpectationFulfillment = false;

	@Override
	public void findMatchEveryHour() {
		findMatch();
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
	public CandidateEntity findMatchForCandidate(CandidateEntity candidateE) {
		CandidateEntity result = candidateE;
		for (PositionEntity positionE : positionRepo.findAllNotUsedPositions(candidateE)) {
			double res = 0;
			res += matchSkillExp(candidateE, positionE);
			res += matchTechExp(candidateE, positionE);
			res += matchSalaryExp(candidateE, positionE);
			res += matchLocationExp(candidateE, positionE);
			System.out.println("findMatch: " + res);
			if ((res >= MATCH_CONDITION) && (candidateE.getNewCount() < MAX_NEW_PROCESSES_FOR_CANDIDATE)) {
				System.out.println("!!!!!!!!!!!!!!!!!!!!!!!      MATCH       !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				ProcessEntity processE = new ProcessEntity();
				processE.setCandidate(candidateE);
				processE.setPosition(positionE);
				processE.setStatus(StatusEnum.NEW);
				candidateE.setNewCount(candidateE.getNewCount() + 1);
				processE.setTechnologiesExpectationFulfillment(technologiesExpectationFulfillment);
				processE.setSalaryExpectationFulfillment(salaryExpectationFulfillment);
				processE.setLocationExpectationFulfillment(locationExpectationFulfillment);
//				processRepo.save(processE);
				candidateE.getProcesses().add(processE);
				result = candidateRepo.save(candidateE);
			}
		}
		return result;
	}

	private double matchLocationExp(CandidateEntity candidateE, PositionEntity positionE) {
		locationExpectationFulfillment = false;
		double res = 0;
		if (candidateE.getExpectation().getLocation() == positionE.getLocation()) {
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
		double res = (double) (count) / candidateE.getExpectation().getTechStack().size();
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
			res = (double) (count) / positionE.getTechStack().size();
		}
		return MAX_POINTS * res;
	}

}
