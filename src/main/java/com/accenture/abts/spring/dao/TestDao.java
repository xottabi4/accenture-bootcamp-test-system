package com.accenture.abts.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.Test;

public interface TestDao extends CrudRepository<Test, Long> {
	Test findByNameAndIsAlive(String name, Boolean isAlive);
}
