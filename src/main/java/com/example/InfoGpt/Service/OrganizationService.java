package com.example.InfoGpt.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.InfoGpt.Entity.Organization;
import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Enums.OrganizationQueryType;
import com.example.InfoGpt.Repositories.OrganizationRepository;

@Service
public class OrganizationService implements InfoGpt {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public ResponseEntity<?> getDetails(String name, FacultyAndHrQueryType type, OrganizationQueryType orgType) {
		Optional<Organization> org = organizationRepository.findByName(name);
		if (org.isEmpty())
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Map.of("response", "The data for the organization " + name + " is not available"));
		switch (orgType) {
		case YEAR_OF_EST:
			int estYear = org.get().getYearOfEst();
			int periodTime = LocalDate.now().getYear() - estYear;
			String result = "The Organization is established in the year " + estYear + ", which is " + periodTime
					+ " years back";
			return ResponseEntity.ok(Map.of("response", result));
		case STUDENTS_COUNT:
			return ResponseEntity.ok(Map.of("response", "Total number of students placed by the organization " + name
					+ " are " + org.get().getTotalStudentPlaced()));
		case FACULTY_COUNT:
			return ResponseEntity.ok(Map.of("response", "Total number of faculties present in the organization " + name
					+ " are " + org.get().getFaculties().size()));
		case HR_COUNT:
			return ResponseEntity.ok(Map.of("response",
					"Total number of hr's present in the organization " + name + " are " + org.get().getHr().size()));
		default:
			return ResponseEntity.ok("No Data Found relaed to the given query");
		}
	}

}
