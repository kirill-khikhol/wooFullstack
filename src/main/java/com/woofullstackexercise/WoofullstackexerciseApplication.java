package com.woofullstackexercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.jdbc.Expectation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.EmployerEntity;
import com.woofullstackexercise.entities.ExpectationEntity;
import com.woofullstackexercise.entities.PositionEntity;
import com.woofullstackexercise.enumirations.LocationEnum;
import com.woofullstackexercise.repository.CandidateRepo;
import com.woofullstackexercise.repository.EmployerRepo;
import com.woofullstackexercise.repository.ILocationRepo;
import com.woofullstackexercise.repository.ITechRepo;
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
//		initTechList();
		initCandidates(5);
		initEmployers();
//		List<String> t = Arrays.asList("Git", "java");
//		System.out.println(cRepo.getAllByTech(t).size());
//		for (CandidateEntity ce : cRepo.getAllByTech(t)) {
////			ce.toString();
//			System.out.println(ce.getName());
//		}
		matchService.findMatchEveryHour();
	}

	private void initEmployers() {
		EmployerEntity employerE = new EmployerEntity();
		employerE.setName("Burito Burito Taco Man");
		Set<PositionEntity> positions = new HashSet<>();
		PositionEntity posE = new PositionEntity();
		posE.setTitle("Java Expert");
		posE.setLocation(LocationEnum.TEL_AVIV);
		posE.setSalary(25000);
		Set<String> techStack = new HashSet<>();
		techStack.add(ITechRepo.TECH_LIST.get(0));
		techStack.add(ITechRepo.TECH_LIST.get(2));
		techStack.add(ITechRepo.TECH_LIST.get(9));
		techStack.add(ITechRepo.TECH_LIST.get(10));
		posE.setTechStack(techStack);
		positions.add(posE);

		employerE.setPositions(positions);
		posE = new PositionEntity();
		posE.setTitle("Web Expert");
		posE.setLocation(LocationEnum.TEL_AVIV);
		posE.setSalary(24000);
		techStack = new HashSet<>();
		techStack.add(ITechRepo.TECH_LIST.get(3));
		techStack.add(ITechRepo.TECH_LIST.get(12));
		techStack.add(ITechRepo.TECH_LIST.get(7));
		techStack.add(ITechRepo.TECH_LIST.get(6));
		posE.setTechStack(techStack);
		positions.add(posE);
		eRepo.save(employerE);
	}

	private void initCandidates(int count) {
		for (int i = 0; i < count; i++) {
			CandidateEntity candidateE = new CandidateEntity();
			candidateE.setName("John " + i);
			ExpectationEntity expE = new ExpectationEntity();
			expE.setSalary(15000 + 500 * i);
			expE.setLocation(LocationEnum.TEL_AVIV);
			for (int j = 0; j < i + 1; j++) {
				expE.getTechStack().add(ITechRepo.TECH_LIST.get(j));
				candidateE.getTechSkills().add(ITechRepo.TECH_LIST.get(j));
			}

			candidateE.setExpectation(expE);

			cRepo.save(candidateE);
		}
	}

	private void initTechList() {
		Set<String> techList = new HashSet<>();
		techList.add("java");
		techList.add("pyton");
		techList.add("ruby");
		techList.add("linux");
		techList.add("REST");
		techList.add("OOP");
		techList.add("Hibernate");
		techList.add("Maven");
		techList.add("Git");
		techList.add("MySQL");
	}
}
