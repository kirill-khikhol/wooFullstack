package com.woofullstackexercise.controller;

import java.util.Set;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.woofullstackexercise.dto.CandidateDto;
import com.woofullstackexercise.dto.ProcessDto;
import com.woofullstackexercise.service.ICandidateService;
import com.woofullstackexercise.service.IProcessService;

@RestController
@CrossOrigin("*")
public class Controller {
	@Autowired
	ICandidateService candidateServise;
	@Autowired
	IProcessService processService;

	@GetMapping
	public String hello() {
		return "hello";
	}

	@GetMapping("/candidate")
	public CandidateDto getCandidate(@RequestParam(value = "name") String name) {
		return candidateServise.findById(name);
	}

	@PutMapping("/process/accept")
	public ProcessDto acceptProcessStatus(@RequestParam(value = "processId") String processId) {
		System.out.println("processId: " + processId);
		return processService.acceptProcessStatus(processId);
	}

	@PutMapping("/process/reject")
	public ProcessDto rejectProcessStatus(@RequestParam(value = "processId") String processId) {
		return processService.rejectProcessStatus(processId);
	}
}
