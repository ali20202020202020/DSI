package com.example.demo.controller;

import com.example.demo.dto.PatientDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
    public class PatientController {
        private final PatientService patientService;

        @Autowired
        public PatientController(PatientService patientService) {
            this.patientService = patientService;
        }

    @GetMapping("/patient_registration")
    public String showPatientRegistrationForm(Model model){
        PatientDto patient = new PatientDto();
        model.addAttribute("patient", patient);

        return "patient_registration";
    }

        @PostMapping("/patient_registration/save")
        public String savePatient(@Valid @ModelAttribute("patient") PatientDto patientDto, BindingResult result, Model model) {
            // Check if the patient with the same phone number already exists
            Patient existingPatient = patientService.findByPhoneNumber(patientDto.getPhoneNumber());
            if (existingPatient != null) {
                result.rejectValue("phoneNumber", null, "A patient with the same phone number already exists");
            }

            // Check for validation errors
            if (result.hasErrors()) {
                model.addAttribute("patient", patientDto);
                return "patient_registration";
            }

            // Save the patient
            patientService.savePatient(patientDto);
            return "redirect:/patient_registration?success";
        }
}
