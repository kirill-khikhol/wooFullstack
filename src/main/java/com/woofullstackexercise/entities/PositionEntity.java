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

import com.woofullstackexercise.enumirations.LocationEnum;

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
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<String> techStack = new HashSet<>();
	private Integer salary;
	@Enumerated(EnumType.STRING)
	private LocationEnum location;
}
