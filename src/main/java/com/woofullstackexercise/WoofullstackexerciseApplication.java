package com.woofullstackexercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.EmployerEntity;
import com.woofullstackexercise.entities.TechEntity;
import com.woofullstackexercise.repository.CandidateRepo;
import com.woofullstackexercise.repository.EmployerRepo;
import com.woofullstackexercise.repository.TechRepo;

@SpringBootApplication
public class WoofullstackexerciseApplication implements CommandLineRunner {
	@Autowired
	CandidateRepo cRepo;
	@Autowired
	EmployerRepo eRepo;
	@Autowired
	TechRepo tRepo;

	public static void main(String[] args) {
		SpringApplication.run(WoofullstackexerciseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initTechList();
		initCandidates();
		initEmployers();
		List<String> t = Arrays.asList("Git", "java");
		System.out.println(cRepo.getAllByTech(t).size());
		for (CandidateEntity ce : cRepo.getAllByTech(t)) {
//			ce.toString();
			System.out.println(ce.getName());
		}

	}

	private void initEmployers() {
		EmployerEntity employerE = new EmployerEntity();
		employerE.setName("shwarmaTech");
		employerE.setSalary(16000);
		employerE.getTechStack().add(tRepo.findByTechName("java"));
		employerE.getTechStack().add(tRepo.findByTechName("linux"));
		employerE.getTechStack().add(tRepo.findByTechName("REST"));
		eRepo.save(employerE);
		employerE = new EmployerEntity();
		employerE.setName("The Best Company");
		employerE.setSalary(30000);
		employerE.getTechStack().add(tRepo.findByTechName("java"));
		employerE.getTechStack().add(tRepo.findByTechName("linux"));
		employerE.getTechStack().add(tRepo.findByTechName("REST"));
		employerE.getTechStack().add(tRepo.findByTechName("Git"));
		employerE.getTechStack().add(tRepo.findByTechName("Hibernate"));
		eRepo.save(employerE);
	}

	private void initCandidates() {
		CandidateEntity candidateE = new CandidateEntity();
		candidateE.setName("John");
		candidateE.setSalary(15000);
		candidateE.getTechStack().add(tRepo.findByTechName("java"));
		candidateE.getTechStack().add(tRepo.findByTechName("linux"));
		candidateE.getTechStack().add(tRepo.findByTechName("REST"));
		candidateE.getTechStack().add(tRepo.findByTechName("OOP"));
		cRepo.save(candidateE);
		candidateE = new CandidateEntity();
		candidateE.setName("Ann");
		candidateE.setSalary(21000);
		candidateE.getTechStack().add(tRepo.findByTechName("java"));
		candidateE.getTechStack().add(tRepo.findByTechName("linux"));
		candidateE.getTechStack().add(tRepo.findByTechName("REST"));
		candidateE.getTechStack().add(tRepo.findByTechName("OOP"));
		candidateE.getTechStack().add(tRepo.findByTechName("Git"));
		candidateE.getTechStack().add(tRepo.findByTechName("Hibernate"));
		candidateE.getTechStack().add(tRepo.findByTechName("MySQL"));
		cRepo.save(candidateE);
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
		for (String string : techList) {
			tRepo.save(new TechEntity(string));
		}

	}
}
