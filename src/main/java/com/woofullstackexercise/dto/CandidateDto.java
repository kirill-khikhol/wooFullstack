package com.woofullstackexercise.dto;

import java.io.Serializable;
import java.util.Set;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.ExpectationEntity;
import com.woofullstackexercise.entities.ProcessEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	private String name;
	private Set<String> techSkills;
	private ExpectationDto expectation;
	private Set<ProcessDto> processes;

	public CandidateDto(CandidateEntity c) {
		super();
		this.name = c.getName();
		this.techSkills = c.getStringTechSkills();
		this.expectation = new ExpectationDto(c.getExpectation());
		this.processes = c.getProcessDto();
	}

}
