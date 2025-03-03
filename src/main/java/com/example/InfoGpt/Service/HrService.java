package com.example.InfoGpt.Service;

import com.example.InfoGpt.Entity.Faculty;
import com.example.InfoGpt.Entity.HR;
import com.example.InfoGpt.Enums.Type;
import com.example.InfoGpt.Repositories.HrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class HrService implements InfoGpt{
    @Autowired
    private HrRepository hrRepository;
    @Override
    public ResponseEntity<?> getDetails(String name, Type type) {
        switch (type) {
            case NAME:
                Optional<HR> Hr = hrRepository.findByName(name);
                if (Hr.isEmpty())
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(Map.of("response", "The data is not found for the requested faculty name " + name));
                return ResponseEntity.ok(Hr);
            case ORGNAME:
                List<HR> orgHr = hrRepository.findByOrganization(name);
                if (orgHr.isEmpty())
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
                            "The data is not found for the requested faculties belongs to organization " + name));
                return ResponseEntity.ok(orgHr);
            case ALL:
                List<HR> allHr = hrRepository.findAll();
                if (allHr.isEmpty())
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("response",
                            "The data is not found"));
                return ResponseEntity.ok(allHr);
            default:
                return ResponseEntity.ok("No Data Found relaed to the given query");
        }
    }
}
