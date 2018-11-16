package com.woofullstackexercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.woofullstackexercise.entities.EmployerEntity;

public interface EmployerRepo extends JpaRepository<EmployerEntity, String> {

}
