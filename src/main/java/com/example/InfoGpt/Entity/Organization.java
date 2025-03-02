package com.example.InfoGpt.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Organization {

	@Id
	private String name;
	private int yearOfEst;
	private long totalStudentPlaced;
	@OneToMany(mappedBy = "organization")
	@JsonIgnore
	private List<HR> hr;
	@OneToMany(mappedBy = "organization")
	@JsonIgnore
	private List<Faculty> faculties;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getYearOfEst() {
		return yearOfEst;
	}

	public void setYearOfEst(int yearOfEst) {
		this.yearOfEst = yearOfEst;
	}

	public long getTotalStudentPlaced() {
		return totalStudentPlaced;
	}

	public void setTotalStudentPlaced(long totalStudentPlaced) {
		this.totalStudentPlaced = totalStudentPlaced;
	}

	public List<HR> getHr() {
		return hr;
	}

	public void setHr(List<HR> hr) {
		this.hr = hr;
	}

	public List<Faculty> getFaculties() {
		return faculties;
	}

	public void setFaculties(List<Faculty> faculties) {
		this.faculties = faculties;
	}

}
