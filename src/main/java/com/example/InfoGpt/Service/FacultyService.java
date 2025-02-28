package com.example.InfoGpt.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.InfoGpt.Entity.Faculty;
import com.example.InfoGpt.Enums.Type;
import com.example.InfoGpt.Repositories.FacultyRepository;

@Service
public class FacultyService implements InfoGpt {

	@Autowired
	private FacultyRepository facultyRepository;

	@Override
	public ResponseEntity<?> getDetails(String name, Type type) {
		switch (type) {
		case NAME:
			Faculty faculty = facultyRepository.findByName(name);
			return ResponseEntity.ok(faculty);
		case ORGNAME:
			List<Faculty> orgFaculties = facultyRepository.findByOrganization(name);
			return ResponseEntity.ok(orgFaculties);
		case ALL:
			List<Faculty> allFaculties = facultyRepository.findAll();
			return ResponseEntity.ok(allFaculties);
		default:
			return ResponseEntity.ok("No Data Found relaed to the given query");
		}
	}
}
