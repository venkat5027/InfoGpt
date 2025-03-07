package com.example.InfoGpt.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.InfoGpt.Entity.Question;
import com.example.InfoGpt.Service.InfoGptService;

@RestController
public class InfoController {

	@Autowired
	private InfoGptService infoGptService;

	@GetMapping("/getinfo")
	public ResponseEntity<?> fetchInformation(@RequestParam String qst) {
		return infoGptService.getInfo(new Question(qst));
	}

}
