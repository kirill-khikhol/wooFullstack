package com.woofullstackexercise.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
	@OneToMany(cascade = CascadeType.ALL)
	private Set<PositionEntity> positions;
}
