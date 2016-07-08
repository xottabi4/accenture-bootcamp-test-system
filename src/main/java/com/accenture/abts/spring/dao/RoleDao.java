package com.accenture.abts.spring.dao;

import org.springframework.data.repository.CrudRepository;

import com.accenture.abts.spring.models.Role;

public interface RoleDao extends CrudRepository<Role, Long> {
}
