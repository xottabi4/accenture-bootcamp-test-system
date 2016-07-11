package com.accenture.abts.spring.models;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "test")
public class Test implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2047128565240197601L;

	@Id
	@Column(name = "test_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Column(name = "test_name")
	private String name;

	@NotNull
	@Column(name = "duration")
	private Long duration;

	@NotNull
	@Column(name = "is_alive")
	private Boolean isAlive;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "test", cascade = CascadeType.ALL)
	@JsonBackReference
	private Set<UserTest> userTests;

	public Test() {

	}

	public Test(String name, Long duration, Boolean isAlive) {
		super();
		this.name = name;
		this.duration = duration;
		this.isAlive = isAlive;
	}

	public Test(Long id, String name, Long duration, Boolean isAlive) {
		super();
		this.id = id;
		this.name = name;
		this.duration = duration;
		this.isAlive = isAlive;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Boolean getIsAlive() {
		return isAlive;
	}

	public void setIsAlive(Boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Set<UserTest> getUserTests() {
		return userTests;
	}

	public void setUserTests(Set<UserTest> userTests) {
		this.userTests = userTests;
	}

}
