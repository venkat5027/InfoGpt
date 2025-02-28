package com.example.InfoGpt.Factorymethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Service.FacultyService;
import com.example.InfoGpt.Service.InfoGpt;

@Component
public class InfoGPTFactory {

	private FacultyService facultyService;

	@Autowired
	public InfoGPTFactory(FacultyService facultyService) {
		this.facultyService = facultyService;
	}

	public InfoGpt getInfoGptFactoryobject(InfoGptType type) {
		switch (type) {
		case FACULTY:
			return facultyService;
		default:
			return null;
		}
	}
}
