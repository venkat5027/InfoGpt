package com.example.InfoGpt.Service;

import org.springframework.http.ResponseEntity;

import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Enums.OrganizationQueryType;

public interface InfoGpt {

	public ResponseEntity<?> getDetails(String name, FacultyAndHrQueryType type, OrganizationQueryType orgType);

}
