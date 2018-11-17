package com.woofullstackexercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woofullstackexercise.dto.CandidateDto;
import com.woofullstackexercise.enumirations.StatusEnum;
import com.woofullstackexercise.service.ICandidateService;

@RestController
@CrossOrigin("*")
public class Controller {
	@Autowired
	ICandidateService candidateServise;

//	@GetMapping
//	public String hello() {
//		return "hello";
//	}

	@GetMapping("/candidate")
	public CandidateDto getCandidate(@RequestParam(value = "name") String name) {
		return candidateServise.findById(name);
	}

	@PutMapping("/process/accept")
	public CandidateDto acceptProcessStatus(@RequestParam(value = "processId") String processId) {
		return candidateServise.changeProcessStatus(processId, StatusEnum.ACCEPTED);
	}

	@PutMapping("/process/reject")
	public CandidateDto rejectProcessStatus(@RequestParam(value = "processId") String processId) {
		return candidateServise.changeProcessStatus(processId, StatusEnum.REJECTED);
	}

	@PutMapping("/candidate/a")
	public CandidateDto acceptProcessStatustest() {
		return candidateServise.changeProcessStatus("20", StatusEnum.ACCEPTED);
	}
}
