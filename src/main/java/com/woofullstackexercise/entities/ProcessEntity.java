package com.woofullstackexercise.entities;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEntity {
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private CandidateEntity candidate;

	@ManyToOne
	private PositionEntity position;
	@NotNull
	private String status;
	@CreationTimestamp
	private LocalDateTime creationTime;
	private int technologiesExpectationFulfillment;
	private boolean salaryExpectationFulfillment;
	private boolean locationExpectationFulfillment;
}
