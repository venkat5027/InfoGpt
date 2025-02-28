package com.example.InfoGpt.Controller;

import com.example.InfoGpt.Entity.Question;
import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Enums.Type;
import com.example.InfoGpt.Factorymethods.InfoGPTFactory;
import com.example.InfoGpt.Service.FacultyService;
import com.example.InfoGpt.Service.InfoGpt;
import com.example.InfoGpt.constants.InfoGptConstants;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

	@GetMapping("/getinfo")
	public ResponseEntity<?> fetchInformation(@RequestBody Question question) {
		InfoGpt infoGpt = null;
		String name = "";
		Type type = Type.ALL;
		if (question.getQuestion().contains("faculty")) {
			infoGpt = InfoGPTFactory.getInfoGptFactoryobject(InfoGptType.FACULTY);
			if (question.getQuestion().contains(InfoGptConstants.FACULTY_OF_ORGANIZATION)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.FACULTY_OF_ORGANIZATION)
								+ InfoGptConstants.FACULTY_OF_ORGANIZATION.length() + 1);
				type = Type.ORGNAME;
			} else if (question.getQuestion().contains(InfoGptConstants.FACULTY)) {
				name = question.getQuestion().substring(question.getQuestion().indexOf(InfoGptConstants.FACULTY)
						+ InfoGptConstants.FACULTY.length() + 1);
				type = Type.NAME;
			}
		}
		return infoGpt.getDetails(name, type);
	}
}
