package com.woofullstackexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.woofullstackexercise.entities.ProcessEntity;

public interface ProcessRepo extends JpaRepository<ProcessEntity, Long> {
}
