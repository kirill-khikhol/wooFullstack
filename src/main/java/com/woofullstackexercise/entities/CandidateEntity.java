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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.woofullstackexercise.dto.ProcessDto;
import com.woofullstackexercise.enumirations.Technologies;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expectation == null) ? 0 : expectation.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((processes == null) ? 0 : processes.hashCode());
		result = prime * result + ((techSkills == null) ? 0 : techSkills.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CandidateEntity other = (CandidateEntity) obj;
		if (expectation == null) {
			if (other.expectation != null)
				return false;
		} else if (!expectation.equals(other.expectation))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (processes == null) {
			if (other.processes != null)
				return false;
		} else if (!processes.equals(other.processes))
			return false;
		if (techSkills == null) {
			if (other.techSkills != null)
				return false;
		} else if (!techSkills.equals(other.techSkills))
			return false;
		return true;
	}
}
