package com.example.InfoGpt.Service;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	public FileStorageService(@Value("${file.upload-dir}") String uploadDir) {
		this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
	}

	// Retrieve file path
	public Path loadFile(String fileName) {
		return this.fileStorageLocation.resolve(fileName).normalize();
	}
}
