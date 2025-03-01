package com.example.InfoGpt.Service;

import org.springframework.http.ResponseEntity;

import com.example.InfoGpt.Enums.FacultyAndHrQueryType;

public interface InfoGpt {

	public ResponseEntity<?> getDetails(String name, FacultyAndHrQueryType type);
}
