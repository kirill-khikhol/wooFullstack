package com.woofullstackexercise.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployerEntity {
	@Id
	private String name;
	@ManyToMany
	private Set<TechEntity> techStack = new HashSet<>();
	private Integer salary;
	@ElementCollection
	private Set<String> location = new HashSet<>();
}
