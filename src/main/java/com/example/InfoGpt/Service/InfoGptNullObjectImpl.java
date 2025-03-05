package com.example.InfoGpt.Service;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Enums.OrganizationQueryType;

public class InfoGptNullObjectImpl implements InfoGpt {

	@Override
	public ResponseEntity<?> getDetails(String name, FacultyAndHrQueryType type, OrganizationQueryType orgType) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Map.of("response", "Please correct your query, can't able to process the given query"));
	}

}
