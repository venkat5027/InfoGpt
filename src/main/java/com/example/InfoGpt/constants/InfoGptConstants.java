package com.example.InfoGpt.constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class InfoGptConstants {
	public static String FACULTY = "faculty";
	public static String FACULTY_OF_ORGANIZATION = "faculty details of organization";
	public static String FACULTY_BY_NAME = "faculty details of";
	//HR-related constants
	public static String HR = "Hr";
	public static String HR_OF_ORGANIZATION = "Hr details of organization";
	public static String HR_BY_NAME = "Hr details of";

	// Technology-related constants
	public static final String KEYWORDS_SET = "keywords";

	static {
		// Initialize the Set with the constants
		Set<String> keywords = new HashSet<>();
		keywords.add("Java");
		keywords.add("Python");
		keywords.add("Mainframes");
		keywords.add("Data_Scince");

		// Make the Set unmodifiable
		//KEYWORDS_SET = Collections.unmodifiableSet(keywords);
	}
}
//	public static final String KEYWORD_HR = "HR details of"; // Alternative for HR keyword
//	public static final String KEYWORD_FACULTY = "Faculty details of"; // Example for faculty keyword
//	public static final String KEYWORD_HR_OF_ORGANIZATION = " Details of Organization"; // Example for organizatio


