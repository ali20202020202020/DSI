package com.example.demo.impl;

import com.example.demo.dto.PatientDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Patient;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.repository.PatientRepository;
import com.example.demo.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class PatientServiceImpl implements PatientService {
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;

    public PatientServiceImpl(PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void savePatient(PatientDto patientDto) {
        Patient patient = new Patient();

        patient.setName(patientDto.getName());
       patient.setLastName(patientDto.getLastName());
       patient.setPhoneNumber(patientDto.getPhoneNumber());
       patient.setAddress(patientDto.getAddress());

        // Encrypt the password using Spring Security


       // patient.setAppointment(Arrays.asList(a));
        patientRepository.save(patient);

    }

    @Override
    public Patient findByPhoneNumber(String phoneNumber) {
        return patientRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public List<PatientDto> findAllPatient() {
        List<Patient> patients = patientRepository.findAll();

        return patients.stream()
                .map((patient) -> mapToPatientDto(patient))
                .collect(Collectors.toList());
    }
    private PatientDto mapToPatientDto(Patient patient) {
        PatientDto patientDto = new PatientDto();


        patientDto.setName(patient.getName());
        patientDto.setLastName(patient.getLastName());
        patientDto.setPhoneNumber(patient.getPhoneNumber());
        patientDto.setAddress(patient.getAddress());
        return patientDto;
    }
}
