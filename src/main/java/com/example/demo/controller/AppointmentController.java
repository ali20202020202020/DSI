package com.example.demo.controller;

import com.example.demo.dto.AppointmentDto;
import com.example.demo.dto.PatientDto;
import com.example.demo.service.AppoinmentService;

import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller
public class AppointmentController {

    private final AppoinmentService appointmentService;
@Autowired
    PatientService patientService;
    @Autowired
    public AppointmentController(AppoinmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointment_registration")
    public String showAppointmentRegistrationForm(Model model) {
        List<PatientDto> patients = patientService.findAllPatient();
        AppointmentDto appointment = new AppointmentDto();
        model.addAttribute("patients", patients);
        model.addAttribute("appointment", appointment);
        return "appointment_registration";
    }

    @PostMapping("/appointment_registration/save")
    public String saveAppointment(@Valid @ModelAttribute("appointment") AppointmentDto appointmentDto,
                                  BindingResult result, Model model) {
        // Check for validation errors
        if (result.hasErrors()) {
            model.addAttribute("appointment", appointmentDto);
            return "appointment_registration";
        }

        // Save the appointment
        appointmentService.saveAppointment(appointmentDto);
        return "redirect:/appointment_registration?success";
    }

    @GetMapping("/appointments")
    public String listAppointments(Model model) {
        List<AppointmentDto> appointments = appointmentService.findAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";
    }
}
