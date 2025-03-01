package com.example.InfoGpt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.InfoGpt.Entity.Question;
import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Factorymethods.InfoGPTFactory;
import com.example.InfoGpt.Service.InfoGpt;
import com.example.InfoGpt.Service.InfoGptNullObject;
import com.example.InfoGpt.constants.InfoGptConstants;

@RestController
public class InfoController {

	@Autowired
	private InfoGPTFactory infoGPTFactory;

	@GetMapping("/getinfo")
	public ResponseEntity<?> fetchInformation(@RequestBody Question question) {
		InfoGpt infoGpt = new InfoGptNullObject();
		String name = "";
		FacultyAndHrQueryType type = FacultyAndHrQueryType.ALL;
		if (question.getQuestion().contains(InfoGptConstants.FACULTY)) {
			infoGpt = infoGPTFactory.getInfoGptFactoryobject(InfoGptType.FACULTY);
			if (question.getQuestion().contains(InfoGptConstants.FACULTY_OF_ORGANIZATION)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.FACULTY_OF_ORGANIZATION)
								+ InfoGptConstants.FACULTY_OF_ORGANIZATION.length() + 1);
				type = FacultyAndHrQueryType.ORGNAME;
			} else if (question.getQuestion().contains(InfoGptConstants.FACULTY_BY_NAME)) {
				name = question.getQuestion().substring(question.getQuestion().indexOf(InfoGptConstants.FACULTY_BY_NAME)
						+ InfoGptConstants.FACULTY_BY_NAME.length() + 1);
				type = FacultyAndHrQueryType.NAME;
			} else {
				name = getKeyword(question.getQuestion());
				if (!name.equals("")) {
					type = FacultyAndHrQueryType.KEYWORD;
				}
			}
		} else if (question.getQuestion().contains(InfoGptConstants.HR)) {
			infoGpt = infoGPTFactory.getInfoGptFactoryobject(InfoGptType.HR);
			if (question.getQuestion().contains(InfoGptConstants.HR_OF_ORGANIZATION)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.HR_OF_ORGANIZATION)
								+ InfoGptConstants.HR_OF_ORGANIZATION.length() + 1);
				type = FacultyAndHrQueryType.ORGNAME;
			} else if (question.getQuestion().contains(InfoGptConstants.HR_BY_NAME)) {
				name = question.getQuestion().substring(question.getQuestion().indexOf(InfoGptConstants.HR_BY_NAME)
						+ InfoGptConstants.HR_BY_NAME.length() + 1);
				type = FacultyAndHrQueryType.NAME;
			}
		}
		return infoGpt.getDetails(name, type);
	}

	private String getKeyword(String question) {
		String[] arrStr = question.split(" ");
		for (String str : arrStr) {
			if (InfoGptConstants.KEYWORDS.contains(str))
				return str;
		}
		return "";
	}
}
