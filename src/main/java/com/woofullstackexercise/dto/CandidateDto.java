package com.woofullstackexercise.dto;

import java.io.Serializable;
import java.util.Set;

import com.woofullstackexercise.entities.CandidateEntity;
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
	private int newCount;
	private int acceptedCount;
	private int rejectedCount;

	public CandidateDto(CandidateEntity c) {
		super();
		this.name = c.getName();
		this.techSkills = c.getStringTechSkills();
		this.expectation = new ExpectationDto(c.getExpectation());
		this.processes = c.getProcessDto();
		this.newCount = c.getNewCount();
		this.acceptedCount = c.getAcceptedCount();
		this.rejectedCount = c.getRejectedCount();

	}

}
