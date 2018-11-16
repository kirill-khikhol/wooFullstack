package com.woofullstackexercise.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;

import com.woofullstackexercise.entities.PositionEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private Set<String> techStack;
	private Integer salary;
	private String location;

	public PositionDto(PositionEntity p) {
		super();
		this.title = p.getTitle();
		this.techStack = p.getTechStack();
		this.salary = p.getSalary();
		this.location = p.getLocation().toString();
	}

}
