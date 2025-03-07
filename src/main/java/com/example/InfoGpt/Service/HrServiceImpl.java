package com.example.InfoGpt.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.InfoGpt.Entity.HR;
import com.example.InfoGpt.Entity.Organization;
import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Enums.Gender;
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
			responseStr.append("The requested details of ").append(name).append(": ");
			if (hr.get().getGender().toUpperCase().equals(Gender.MALE.name()))
				responseStr.append("He is ");
			else
				responseStr.append("she is ");
			responseStr.append(hr.get().getAge()).append(" years old and has ").append(hr.get().getExperience())
					.append("+ years of experience at ").append(hr.get().getOrganizationName());
			return ResponseEntity.ok(Map.of("response", responseStr));
		case ORGNAME:
			Optional<Organization> org = organizationRepository.findByName(name);
			if (org.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Map.of("response", "Mentioned Organization " + name + " is not found"));
			List<HR> orgHrs = hrRepository.findByOrganization(org.get());
			if (orgHrs.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
						"The data is not found for the requested hr belongs to organization " + name));
			if (orgHrs.size() == 1) {
				responseStr = new StringBuffer();
				responseStr.append("The requested details of the hr working at ").append(name).append(": Name is ")
						.append(orgHrs.getFirst().getName());
				if (orgHrs.getFirst().getGender().toUpperCase().equals(Gender.MALE.name()))
					responseStr.append(", He is ");
				else
					responseStr.append(", she is ");
				responseStr.append(orgHrs.getFirst().getAge()).append(" years old and has ")
						.append(orgHrs.getFirst().getExperience()).append("+ years of experience at ")
						.append(orgHrs.getFirst().getOrganizationName());
				return ResponseEntity.ok(Map.of("response", responseStr));
			}
			return ResponseEntity.ok(Map.of("response", orgHrs));
		case ALL:
			List<HR> allFaculties = hrRepository.findAll();
			if (allFaculties.isEmpty())
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response", "The data is not found"));
			return ResponseEntity.ok(Map.of("response", allFaculties));
		default:
			return ResponseEntity.ok(Map.of("response", "No Data Found related to the given query"));
		}
	}

}
