package com.example.demo.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString

@Table(name ="appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime time;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    public Appointment(Long id, LocalDateTime time,Patient patient) {
        this.id = id;
        this.time = time;
        this.patient = patient;
    }
}
