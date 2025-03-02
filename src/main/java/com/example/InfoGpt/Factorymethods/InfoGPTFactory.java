package com.example.InfoGpt.Factorymethods;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Service.FacultyService;
import com.example.InfoGpt.Service.HrService;
import com.example.InfoGpt.Service.InfoGpt;
import com.example.InfoGpt.Service.InfoGptNullObject;
import com.example.InfoGpt.Service.OrganizationService;

@Component
public class InfoGPTFactory {

	private FacultyService facultyService;

	private HrService hrService;

	private OrganizationService organizationService;

	@Autowired
	public InfoGPTFactory(FacultyService facultyService, HrService hrService, OrganizationService organizationService) {
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
			return new InfoGptNullObject();
		}
	}
}
