package com.woofullstackexercise.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.woofullstackexercise.enumirations.StatusEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@ManyToOne
	private CandidateEntity candidate;

	@ManyToOne
	private PositionEntity position;
	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusEnum status;
	@CreationTimestamp
	private LocalDateTime creationTime;
	private int technologiesExpectationFulfillment;
	private boolean salaryExpectationFulfillment;
	private boolean locationExpectationFulfillment;

}
