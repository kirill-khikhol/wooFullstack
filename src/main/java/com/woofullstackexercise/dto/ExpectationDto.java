package com.woofullstackexercise.dto;

import java.io.Serializable;
import java.util.Set;

import com.woofullstackexercise.entities.ExpectationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpectationDto implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	private Set<String> techStack;
	private Integer salary;
	private String location;

	public ExpectationDto(ExpectationEntity e) {
		super();
		this.techStack = e.getStringTechStack();
		this.salary = e.getSalary();
		this.location = e.getLocation().toString();
	}
	
}
