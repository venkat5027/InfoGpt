package com.example.InfoGpt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.InfoGpt.Entity.Question;
import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Enums.Type;
import com.example.InfoGpt.Factorymethods.InfoGPTFactory;
import com.example.InfoGpt.Service.InfoGpt;
import com.example.InfoGpt.constants.InfoGptConstants;

@RestController
public class InfoController {

	@Autowired
	private InfoGPTFactory infoGPTFactory;

	@GetMapping("/getinfo")
	public ResponseEntity<?> fetchInformation(@RequestBody Question question) {
		InfoGpt infoGpt = null;
		String name = "";
		Type type = Type.ALL;
		if (question.getQuestion().contains(InfoGptConstants.FACULTY)) {
			infoGpt = infoGPTFactory.getInfoGptFactoryobject(InfoGptType.FACULTY);
			if (question.getQuestion().contains(InfoGptConstants.FACULTY_OF_ORGANIZATION)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.FACULTY_OF_ORGANIZATION)
								+ InfoGptConstants.FACULTY_OF_ORGANIZATION.length() + 1);
				type = Type.ORGNAME;
			} else if (question.getQuestion().contains(InfoGptConstants.FACULTY_BY_NAME)) {
				name = question.getQuestion().substring(question.getQuestion().indexOf(InfoGptConstants.FACULTY_BY_NAME)
						+ InfoGptConstants.FACULTY_BY_NAME.length() + 1);
				type = Type.NAME;
			}
		}
		if (question.getQuestion().contains(InfoGptConstants.HR)) {
			infoGpt = infoGPTFactory.getInfoGptFactoryobject(InfoGptType.HR);
			if (question.getQuestion().contains(InfoGptConstants.HR_OF_ORGANIZATION)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.HR_OF_ORGANIZATION)
								+ InfoGptConstants.HR_OF_ORGANIZATION.length() + 1);
				type = Type.ORGNAME;
			} else if (question.getQuestion().contains(InfoGptConstants.HR_BY_NAME)) {
				name = question.getQuestion().substring(question.getQuestion().indexOf(InfoGptConstants.HR_BY_NAME)
						+ InfoGptConstants.HR_BY_NAME.length() + 1);
				type = Type.NAME;
			}
		}
		//KeyWords controller
		if (question.getQuestion().contains(InfoGptConstants.KEYWORDS_SET)) {
			int keywordIndex = question.getQuestion().indexOf(InfoGptConstants.KEYWORDS_SET);
			if (keywordIndex != -1) {
				name = question.getQuestion().substring(keywordIndex + InfoGptConstants.KEYWORDS_SET.length()).trim();
				type = Type.KEYWORD;
			}
		}
		if (infoGpt != null) {
			return infoGpt.getDetails(name, type);
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid query or type not supported");
		}
	}
}



