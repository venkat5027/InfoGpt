package com.example.InfoGpt.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InfoGpt.Entity.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

	public Faculty findByName(String name);

	public List<Faculty> findByOrganization(String name);
}
