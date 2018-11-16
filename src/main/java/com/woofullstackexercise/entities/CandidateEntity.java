package com.woofullstackexercise.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.woofullstackexercise.dto.ProcessDto;
import com.woofullstackexercise.enumirations.Technologies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CandidateEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Technologies> techSkills = new HashSet<>();
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private ExpectationEntity expectation;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "candidate")
	private Set<ProcessEntity> processes;
	private int newCount = 0;
	private int acceptedCount = 0;
	private int rejectedCount = 0;

	public Set<ProcessDto> getProcessDto() {
		Set<ProcessDto> result = new HashSet<>();
		for (ProcessEntity p : processes) {
			result.add(new ProcessDto(p));
		}
		return result;
	}

	public Set<String> getStringTechSkills() {
		Set<String> result = new HashSet<>();
		for (Technologies ts : techSkills) {
			result.add(ts.toString());
		}
		return result;
	}

	

}
