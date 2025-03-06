package com.example.InfoGpt.Factorymethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Service.FacultyServiceImpl;
import com.example.InfoGpt.Service.HrServiceImpl;
import com.example.InfoGpt.Service.InfoGpt;
import com.example.InfoGpt.Service.InfoGptNullObjectImpl;
import com.example.InfoGpt.Service.OrganizationServiceImpl;

@Component
public class InfoGPTFactory {

	private FacultyServiceImpl facultyService;

	private HrServiceImpl hrService;

	private OrganizationServiceImpl organizationService;

	@Autowired
	public InfoGPTFactory(FacultyServiceImpl facultyService, HrServiceImpl hrService, OrganizationServiceImpl organizationService) {
		this.facultyService = facultyService;
		this.hrService = hrService;
		this.organizationService = organizationService;
	}

	public InfoGpt getInfoGptFactoryobject(InfoGptType type) {
		switch (type) {
		case FACULTY:
			return facultyService;
		case HR:
			return hrService;
		case ORGANIZATION:
			return organizationService;
		default:
			return new InfoGptNullObjectImpl();
		}
	}
}
