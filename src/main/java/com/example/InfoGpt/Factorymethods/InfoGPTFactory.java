package com.example.InfoGpt.Factorymethods;

import com.example.InfoGpt.Enums.InfoGptType;
import com.example.InfoGpt.Service.FacultyService;
import com.example.InfoGpt.Service.InfoGpt;

import static com.example.InfoGpt.Enums.InfoGptType.FACULTY;

public class InfoGPTFactory {
    public static InfoGpt getInfoGptFactoryobject(InfoGptType type) {
        switch(type) {
            case FACULTY:
                return new FacultyService();
            default:
                return null;
        }
    }
}
