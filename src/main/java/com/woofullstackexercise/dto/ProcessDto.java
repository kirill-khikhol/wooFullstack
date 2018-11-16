package com.woofullstackexercise.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

import com.woofullstackexercise.entities.CandidateEntity;
import com.woofullstackexercise.entities.PositionEntity;
import com.woofullstackexercise.entities.ProcessEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProcessDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private PositionDto position;
	private String status;
	private LocalDateTime creationTime;
	private int technologiesExpectationFulfillmentAmount;
	private int technologiesExpectationFulfillmentOf;
	private boolean salaryExpectationFulfillment;
	private int salary;
	private boolean locationExpectationFulfillment;
	private String location;

	public ProcessDto(ProcessEntity p) {
		super();
		this.id = p.getId();
		this.position = new PositionDto(p.getPosition());
		this.status = p.getStatus();
		this.creationTime = p.getCreationTime();
		this.technologiesExpectationFulfillmentAmount = p.getTechnologiesExpectationFulfillment();
		this.technologiesExpectationFulfillmentOf = p.getCandidate().getExpectation().getTechStack().size();
		this.salaryExpectationFulfillment = p.isSalaryExpectationFulfillment();
		this.salary = p.getCandidate().getExpectation().getSalary();
		this.locationExpectationFulfillment = p.isLocationExpectationFulfillment();
		this.location = p.getCandidate().getExpectation().getLocation().toString();
	}

}
