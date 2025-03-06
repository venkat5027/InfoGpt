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
import com.example.InfoGpt.Enums.Gender;
import com.example.InfoGpt.Enums.OrganizationQueryType;
import com.example.InfoGpt.Repositories.FacultyRepository;
import com.example.InfoGpt.Repositories.OrganizationRepository;

@Service
public class FacultyServiceImpl implements InfoGpt {

	@Autowired
	private FacultyRepository facultyRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public ResponseEntity<?> getDetails(String name, FacultyAndHrQueryType type, OrganizationQueryType orgType) {
		StringBuffer responseStr;
		switch (type) {
		case NAME:
			Optional<Faculty> faculty = facultyRepository.findByName(name);
			if (faculty.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "The data is not found for the requested faculty name " + name));
			responseStr = new StringBuffer();
			responseStr.append("The requested details of " + name + ": ");
			if (faculty.get().getGender().toUpperCase().equals(Gender.MALE.name()))
				responseStr.append("He is ");
			else
				responseStr.append("she is ");
			responseStr.append(faculty.get().getAge())
					.append(" years old and has " + faculty.get().getExperience() + "+ years of experience in "
							+ faculty.get().getProgrammingLanguage() + " technology at "
							+ faculty.get().getOrganizationName());
			return ResponseEntity.ok(Map.of("response", responseStr));
		case KEYWORD:
			List<Faculty> keywordFaculties = facultyRepository.findByProgrammingLanguage(name);
			if (keywordFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
						Map.of("response", "The data of faculty is not found with the requested keyword " + name));
			if (keywordFaculties.size() == 1) {
				responseStr = new StringBuffer();
				responseStr.append("The requested details of the faculty teaching " + name + " technology: Name is "
						+ keywordFaculties.get(0).getName());
				if (keywordFaculties.get(0).getGender().toUpperCase().equals(Gender.MALE.name()))
					responseStr.append(", He is ");
				else
					responseStr.append(", she is ");
				responseStr.append(keywordFaculties.get(0).getAge())
						.append(" years old and has " + keywordFaculties.get(0).getExperience()
								+ "+ years of experience in " + keywordFaculties.get(0).getProgrammingLanguage()
								+ " technology at " + keywordFaculties.get(0).getOrganizationName());
				return ResponseEntity.ok(Map.of("response", responseStr));
			}
			return ResponseEntity.ok(Map.of("response", keywordFaculties));
		case ORGNAME:
			Optional<Organization> org = organizationRepository.findByName(name);
			if (org.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "Mentioned Organization " + name + " is not found"));
			List<Faculty> orgFaculties = facultyRepository.findByOrganization(org.get());
			if (orgFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
						"The data is not found for the requested faculties belongs to organization " + name));
			if (orgFaculties.size() == 1) {
				responseStr = new StringBuffer();
				responseStr.append("The requested details of the faculty working at " + name + ": Name is "
						+ orgFaculties.get(0).getName());
				if (orgFaculties.get(0).getGender().toUpperCase().equals(Gender.MALE.name()))
					responseStr.append(", He is ");
				else
					responseStr.append(", she is ");
				responseStr.append(orgFaculties.get(0).getAge())
						.append(" years old and has " + orgFaculties.get(0).getExperience()
								+ "+ years of experience in " + orgFaculties.get(0).getProgrammingLanguage()
								+ " technology");
				return ResponseEntity.ok(Map.of("response", responseStr));
			}
			return ResponseEntity.ok(Map.of("response", orgFaculties));
		case ALL:
			List<Faculty> allFaculties = facultyRepository.findAll();
			if (allFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response", "The data is not found"));
			return ResponseEntity.ok(Map.of("response", allFaculties));
		default:
			return ResponseEntity.ok(Map.of("response", "No Data Found relaed to the given query"));
		}
	}
}
