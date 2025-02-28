package com.example.InfoGpt.Factorymethods;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.InfoGpt.Entity.Faculty;
import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Repositories.FacultyRepository;
import com.example.InfoGpt.Service.FacultyService;
import com.example.InfoGpt.Service.InfoGpt;

public class InfoGPTFactory {
	public static InfoGpt getInfoGptFactoryobject(InfoGptType type) {
		switch (type) {
		case FACULTY:
			ApplicationContext context = new AnnotationConfigApplicationContext(Faculty.class, FacultyRepository.class);
			InfoGpt info = new FacultyService();
			context.getAutowireCapableBeanFactory().autowireBean(info);
			return info;
		default:
			return null;
		}
	}
}
