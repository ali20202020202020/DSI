package com.example.demo.repository;

import com.example.demo.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Date;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Appointment findByTime(LocalDateTime time);
}
