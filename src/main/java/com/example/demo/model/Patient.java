package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString

@Table(name ="patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    public List<Appointment> appointment= new ArrayList<>();

    public Patient(Long id, String name, String lastName, String phoneNumber, String address, List<Appointment> appointment) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.appointment = appointment;
    }
}
