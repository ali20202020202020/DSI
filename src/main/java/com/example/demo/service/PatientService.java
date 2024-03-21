package com.example.demo.service;

import com.example.demo.dto.PatientDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Patient;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PatientService {
    void savePatient(PatientDto patientDto);
    Patient findByPhoneNumber(String phoneNumber);
    List<PatientDto> findAllPatient();

}
