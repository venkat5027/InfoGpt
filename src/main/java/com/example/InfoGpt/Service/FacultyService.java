package com.example.InfoGpt.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.InfoGpt.Entity.Faculty;
import com.example.InfoGpt.Entity.Organization;
import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Enums.OrganizationQueryType;
import com.example.InfoGpt.Repositories.FacultyRepository;
import com.example.InfoGpt.Repositories.OrganizationRepository;

@Service
public class FacultyService implements InfoGpt {

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public ResponseEntity<?> getDetails(String name, FacultyAndHrQueryType type, OrganizationQueryType orgType) {
		switch (type) {
		case NAME:
			Optional<Faculty> faculty = facultyRepository.findByName(name);
			if (faculty.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "The data is not found for the requested faculty name " + name));
			return ResponseEntity.ok(faculty);
		case KEYWORD:
			List<Faculty> keywordFaculties = facultyRepository.findByProgrammingLanguage(name);
			if (keywordFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						Map.of("response", "The data of faculty is not found with the requested keyword " + name));
			return ResponseEntity.ok(keywordFaculties);
		case ORGNAME:
			Optional<Organization> org = organizationRepository.findByName(name);
			if (org.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "Mentioned Organization " + name + " is not found"));
			List<Faculty> orgFaculties = facultyRepository.findByOrganization(org.get());
			if (orgFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
						"The data is not found for the requested faculties belongs to organization " + name));
			return ResponseEntity.ok(orgFaculties);
		case ALL:
			List<Faculty> allFaculties = facultyRepository.findAll();
			if (allFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response", "The data is not found"));
			return ResponseEntity.ok(allFaculties);
		default:
			return ResponseEntity.ok("No Data Found relaed to the given query");
		}
	}
}
