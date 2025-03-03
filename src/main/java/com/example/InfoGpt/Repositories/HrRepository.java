package com.example.InfoGpt.Repositories;

import com.example.InfoGpt.Entity.Faculty;
import com.example.InfoGpt.Entity.HR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HrRepository extends JpaRepository<HR, Long> {
    public Optional<HR> findByName(String name);
    public List<HR> findByOrganization(String name);
}
