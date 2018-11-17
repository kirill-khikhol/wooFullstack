package com.woofullstackexercise;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.EmployerEntity;
import com.woofullstackexercise.entities.ExpectationEntity;
import com.woofullstackexercise.entities.PositionEntity;
import com.woofullstackexercise.enumirations.LocationEnum;
import com.woofullstackexercise.enumirations.Technologies;
import com.woofullstackexercise.repository.CandidateRepo;
import com.woofullstackexercise.repository.EmployerRepo;
import com.woofullstackexercise.service.IMatchService;

@SpringBootApplication
public class WoofullstackexerciseApplication implements CommandLineRunner {
	@Autowired
	CandidateRepo cRepo;
	@Autowired
	EmployerRepo eRepo;
	@Autowired
	IMatchService matchService;

	public static void main(String[] args) {
		SpringApplication.run(WoofullstackexerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initCandidates(5);
		initEmployers();
		matchService.findMatchEveryHour();
	}

	private void initEmployers() {
		String[] companyNames = { "Burito Burito Taco Man", "Best Company", "Sunny Side Inc" };
		String[] positionNames = { "Java Expert", "Web Expert", "Full Stack Developer" };
		EmployerEntity employerE = null;
		for (String companyName : companyNames) {
			employerE = new EmployerEntity();
			employerE.setName(companyName);
			Set<PositionEntity> positions = new HashSet<>();
			for (String positionName : positionNames) {
				PositionEntity posE = new PositionEntity();
				posE.setTitle(positionName);
				posE.setLocation(LocationEnum.TEL_AVIV);
				posE.setSalary(25000);
				Set<Technologies> techStack = new HashSet<>();
				for (int i = 0; i < 5; i++) {
					techStack.add(Technologies.getRandom());
				}
				posE.setTechStack(techStack);
				posE.setEmployer(employerE);
				positions.add(posE);
			}
			employerE.setPositions(positions);
			eRepo.save(employerE);
		}
	}

	private void initCandidates(int count) {
		for (int i = 0; i < count; i++) {
			CandidateEntity candidateE = new CandidateEntity();
			candidateE.setName("John " + i);
			ExpectationEntity expE = new ExpectationEntity();
			expE.setSalary(15000 + 500 * i);
			expE.setLocation(LocationEnum.TEL_AVIV);
			for (int j = 0; j < 5; j++) {
				expE.getTechStack().add(Technologies.getRandom());
				candidateE.getTechSkills().add(Technologies.getRandom());
			}

			candidateE.setExpectation(expE);

			cRepo.save(candidateE);
		}
	}
}
