package com.example.demo.dto;

import com.example.demo.model.Patient;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDateTime;

public class AppointmentDto {
    private Long id;

    private LocalDateTime time;

    private Patient patient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


}
