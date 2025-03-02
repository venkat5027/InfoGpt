package com.example.InfoGpt.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InfoGpt.Entity.Faculty;
import com.example.InfoGpt.Entity.Organization;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

	public Optional<Faculty> findByName(String name);

	List<Faculty> findByOrganization(Organization organization);
	
	List<Faculty> findByProgrammingLanguage(String programmingLanguage);
}
