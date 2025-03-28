package com.example.InfoGpt.Factorymethods;

import com.example.InfoGpt.Service.HrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Service.FacultyService;
import com.example.InfoGpt.Service.InfoGpt;

@Component
public class InfoGPTFactory {

	private FacultyService facultyService;
	private  HrService hrService;

	@Autowired
	public InfoGPTFactory(FacultyService facultyService ,HrService hrService) {
		this.facultyService = facultyService;
		this.hrService=hrService;
	}

	public InfoGpt getInfoGptFactoryobject(InfoGptType type) {
//
		switch (type) {
			case FACULTY:
				return facultyService;
			case HR: // Add a case for HR
				return hrService;
//			case KEYWORDS:
//				return facultyService;

			default:
				return null;
		}
	}
}
