package com.woofullstackexercise.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.woofullstackexercise.enumirations.LocationEnum;
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
public class PositionEntity {
	@Id
	private String title;
//	@Id
//	@ManyToOne
//	private EmployerEntity employer;
	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	private Set<Technologies> techStack = new HashSet<>();
	private Integer salary;
	@Enumerated(EnumType.STRING)
	private LocationEnum location;

	public Set<String> getStringTechStack() {
		Set<String> result = new HashSet<>();
		for (Technologies ts : techStack) {
			result.add(ts.toString());
		}
		return result;
	}
}
