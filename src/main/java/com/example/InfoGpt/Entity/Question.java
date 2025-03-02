package com.example.InfoGpt.Entity;

public class Question {
	private String question;

	public String getQuestion() {
		return question;
	}

	@Override
	public String toString() {
		return "Question{" + "question='" + question + '\'' + '}';
	}

	public void setQuestion(String question) {
		this.question = question;
	}
}
