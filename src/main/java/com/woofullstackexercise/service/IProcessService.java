package com.woofullstackexercise.service;

import org.springframework.web.bind.annotation.RequestParam;

import com.woofullstackexercise.dto.ProcessDto;

public interface IProcessService {
	public static final String STATUS_ACCEPTED = "Accepted";
	public static final String STATUS_REJECTED = "Rejected";
	public static final String STATUS_NEW = "New";

	ProcessDto acceptProcessStatus(String processId);

	ProcessDto rejectProcessStatus(String processId);
}
