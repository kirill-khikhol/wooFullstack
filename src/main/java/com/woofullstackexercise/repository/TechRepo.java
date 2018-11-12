package com.woofullstackexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woofullstackexercise.entities.TechEntity;

public interface TechRepo extends JpaRepository<TechEntity, Long> {
	TechEntity findByTechName(String tName);
}
