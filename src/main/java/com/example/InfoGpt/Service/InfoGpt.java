package com.example.InfoGpt.Service;

import com.example.InfoGpt.Enums.OrgType;
import com.example.InfoGpt.Enums.Type;
import org.springframework.http.ResponseEntity;

public interface InfoGpt {

    public ResponseEntity<?> getDetails(String name, Type type);
}
