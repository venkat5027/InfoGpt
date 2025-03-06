package com.example.InfoGpt.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.InfoGpt.Enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.InfoGpt.Entity.HR;
import com.example.InfoGpt.Entity.Organization;
import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Enums.OrganizationQueryType;
import com.example.InfoGpt.Repositories.HrRespository;
import com.example.InfoGpt.Repositories.OrganizationRepository;

@Service
public class HrServiceImpl implements InfoGpt {

	@Autowired
	private HrRespository hrRepository;

	@Autowired
	private OrganizationRepository organizationRepository;

	@Override
	public ResponseEntity<?> getDetails(String name, FacultyAndHrQueryType type, OrganizationQueryType orgType) {
		StringBuffer responseStr;
		switch (type) {
		case NAME:
			Optional<HR> hr = hrRepository.findByName(name);
			if (hr.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "The data is not found for the requested hr name " + name));
			responseStr = new StringBuffer();
			responseStr.append("The requested details of " + name + ": ");
			if (hr.get().getGender().toUpperCase().equals(Gender.MALE.name()))
				responseStr.append("He is ");
			else
				responseStr.append("she is ");
			responseStr.append(hr.get().getAge())
					.append(" years old and has " + hr.get().getExperience() + "+ years of experience at "
							+ hr.get().getOrganizationName());
			return ResponseEntity.ok(Map.of("response", responseStr));
		case ORGNAME:
			Optional<Organization> org = organizationRepository.findByName(name);
			if (org.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "Mentioned Organization " + name + " is not found"));
			List<HR> orgFaculties = hrRepository.findByOrganization(org.get());
			if (orgFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
						"The data is not found for the requested hr belongs to organization " + name));
			return ResponseEntity.ok(orgFaculties);
		case ALL:
			List<HR> allFaculties = hrRepository.findAll();
			if (allFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response", "The data is not found"));
			return ResponseEntity.ok(allFaculties);
		default:
			return ResponseEntity.ok("No Data Found relaed to the given query");
		}
	}

}
