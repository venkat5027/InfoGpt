package com.example.InfoGpt.Service;

import com.example.InfoGpt.Enums.Type;
import org.springframework.http.ResponseEntity;

public class FacultyService implements InfoGpt{
    @Override
    public ResponseEntity<?> getDetails(String name, Type type) {
        return null;
    }
}
