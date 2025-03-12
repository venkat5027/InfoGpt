package com.example.InfoGpt.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.InfoGpt.Entity.Question;
import com.example.InfoGpt.Enums.FacultyAndHrQueryType;
import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Enums.OrganizationQueryType;
import com.example.InfoGpt.Factorymethods.InfoGPTFactory;
import com.example.InfoGpt.constants.InfoGptConstants;

@Service
public class InfoGptService {

	@Autowired
	private InfoGPTFactory infoGPTFactory;

	public ResponseEntity<?> getInfo(Question question) {
		InfoGpt infoGpt = new InfoGptNullObjectImpl();
		String name = "";
		FacultyAndHrQueryType type = FacultyAndHrQueryType.ALL;
		OrganizationQueryType orgType = OrganizationQueryType.YEAR_OF_EST;
		if (question.getQuestion().contains(InfoGptConstants.ORGANIZATION)) {
			infoGpt = infoGPTFactory.getInfoGptFactoryobject(InfoGptType.ORGANIZATION);
			if (question.getQuestion().contains(InfoGptConstants.ORG_ESTABLISHMENT)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.ORG_ESTABLISHMENT)
								+ InfoGptConstants.ORG_ESTABLISHMENT.length() + 1);
				orgType = OrganizationQueryType.YEAR_OF_EST;
			} else if (question.getQuestion().contains(InfoGptConstants.STUDENTS_PLACED_BY_ORG)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.STUDENTS_PLACED_BY_ORG)
								+ InfoGptConstants.STUDENTS_PLACED_BY_ORG.length() + 1);
				orgType = OrganizationQueryType.STUDENTS_COUNT;
			} else if (question.getQuestion().contains(InfoGptConstants.TOTAL_NOOF_FACULTIES_IN_ORG)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.TOTAL_NOOF_FACULTIES_IN_ORG)
								+ InfoGptConstants.TOTAL_NOOF_FACULTIES_IN_ORG.length() + 1);
				orgType = OrganizationQueryType.FACULTY_COUNT;
			} else if (question.getQuestion().contains(InfoGptConstants.TOTAL_NOOF_HRS_IN_ORG)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.TOTAL_NOOF_HRS_IN_ORG)
								+ InfoGptConstants.TOTAL_NOOF_HRS_IN_ORG.length() + 1);
				orgType = OrganizationQueryType.HR_COUNT;
			}
		} else if (question.getQuestion().contains(InfoGptConstants.FACULTY)) {
			infoGpt = infoGPTFactory.getInfoGptFactoryobject(InfoGptType.FACULTY);
			if(question.getQuestion().contains(InfoGptConstants.FACULTY_FILES)) {
				name = question.getQuestion()
						.substring(question.getQuestion().indexOf(InfoGptConstants.FACULTY_FILES)
								+ InfoGptConstants.FACULTY_FILES.length() + 1);
				type = FacultyAndHrQueryType.FACULTYFILE;
			}
			else if (question.getQuestion().contains(InfoGptConstants.FACULTY_OF_ORGANIZATION)) {
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
		return infoGpt.getDetails(name, type, orgType);
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
