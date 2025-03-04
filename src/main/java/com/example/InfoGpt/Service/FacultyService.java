package com.example.InfoGpt.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
			Optional<Faculty> faculty = facultyRepository.findByName(name);
			if (faculty.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "The data is not found for the requested faculty name " + name));
			return ResponseEntity.ok(faculty);
		case ORGNAME:
			List<Faculty> orgFaculties = facultyRepository.findByOrganization(name);
			if (orgFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
						"The data is not found for the requested faculties belongs to organization " + name));
			return ResponseEntity.ok(orgFaculties);
		case ALL:
			List<Faculty> allFaculties = facultyRepository.findAll();
			if (allFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
						"The data is not found"));
			return ResponseEntity.ok(allFaculties);
			case KEYWORD:
				List<Faculty> keyWords = facultyRepository.findByprogrammingLanguage(name);
				if (keyWords.isEmpty())
					return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
							"The data is not found for the requested faculties belongs to organization " + name));
				return ResponseEntity.ok(keyWords);


		default:
			return ResponseEntity.ok("No Data Found relaed to the given query");
		}
	}
}
