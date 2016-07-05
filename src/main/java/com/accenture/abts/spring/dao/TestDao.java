package com.accenture.abts.spring.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.Test;

@Transactional
public interface TestDao extends CrudRepository<Test, Long> {

}
