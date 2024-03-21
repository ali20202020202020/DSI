package com.example.demo.service;

import com.example.demo.dto.AppointmentDto;

import java.util.List;

public interface AppoinmentService {
    void saveAppointment(AppointmentDto appointmentDto);
    List<AppointmentDto> findAllAppointments();
}
