package com.woofullstackexercise.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.woofullstackexercise.service.IMatchService;

@Configuration
@EnableScheduling
public class SpringConfig {
	@Autowired
	IMatchService matchService;

	@Scheduled(fixedRate = 1000 * 60 * 60, initialDelay = 1000 * 10)
	public void findMatchEveryHour() {
		matchService.findMatchEveryHour();
	}

}
