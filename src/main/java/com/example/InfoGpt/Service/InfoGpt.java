package com.example.InfoGpt.Service;

import org.springframework.http.ResponseEntity;

import com.example.InfoGpt.Enums.Type;

public interface InfoGpt {

	public ResponseEntity<?> getDetails(String name, Type type);
}
