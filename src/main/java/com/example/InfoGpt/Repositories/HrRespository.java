package com.example.InfoGpt.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InfoGpt.Entity.HR;

@Repository
public interface HrRespository extends JpaRepository<HR, Long> {

	public Optional<HR> findByName(String name);

	public List<HR> findByOrganization(String name);

}
