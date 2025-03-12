package com.example.InfoGpt.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InfoGptConstants {
	public static final String FACULTY = "faculty";
	public static final String FACULTY_FILES = "faculty file of";
	public static final String FACULTY_OF_ORGANIZATION = "faculty details of organization";
	public static final String FACULTY_BY_NAME = "faculty details of";
	public static final Set<String> KEYWORDS = new HashSet<>(
			Arrays.asList("MainFrames", "Python", "C++", "Java", "C#"));

	public static final String HR = "hr";
	public static final String HR_OF_ORGANIZATION = "hr details of organization";
	public static final String HR_BY_NAME = "hr details of";

	public static final String ORGANIZATION = "total number of";
	public static final String ORG_ESTABLISHMENT = "establishment of the organization";
	public static final String STUDENTS_PLACED_BY_ORG = "Students placed by the organization";
	public static final String TOTAL_NOOF_FACULTIES_IN_ORG = "faculties present in the organization";
	public static final String TOTAL_NOOF_HRS_IN_ORG = "hrs present in the organization";
}
