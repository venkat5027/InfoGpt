package com.example.InfoGpt.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.InfoGpt.Entity.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, String> {

	public Optional<Organization> findByName(String orgName);
}
