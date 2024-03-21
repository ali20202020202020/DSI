package com.example.demo.impl;
import com.example.demo.dto.PatientDto;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.PatientRepository;
import com.example.demo.dto.AppointmentDto;
import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppoinmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppoinmentServiceImpl implements AppoinmentService {

    private final AppointmentRepository appointmentRepository;
@Autowired
private PatientRepository patientRepository;

    @Autowired
    public AppoinmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void saveAppointment(AppointmentDto appointmentDto) {
        Appointment appointment = new Appointment();
        appointment.setTime(appointmentDto.getTime());
        // Set patient using mapper or directly if available
        appointment.setPatient(appointmentDto.getPatient());
        appointmentRepository.save(appointment);
    }

    @Override
    public List<AppointmentDto> findAllAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(this::mapToAppointmentDto)
                .collect(Collectors.toList());
    }

    private AppointmentDto mapToAppointmentDto(Appointment appointment) {
        AppointmentDto appointmentDto = new AppointmentDto();
        appointmentDto.setId(appointment.getId());
        appointmentDto.setTime(appointment.getTime());
        appointmentDto.setPatient(appointment.getPatient());
        return appointmentDto;
    }
}
