package com.example.peakflow_app.controller;

import com.example.peakflow_app.model.Measurement;
import com.example.peakflow_app.model.Symptom;
import com.example.peakflow_app.model.TimeOfDay;
import com.example.peakflow_app.repository.MeasurementRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MeasurementController {

    private final MeasurementRepository measurementRepository;

    public MeasurementController(MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    @GetMapping("/record")
    public String showRecordForm(Model model) {
        model.addAttribute("measurement", new Measurement());
        model.addAttribute("timeOfDays", TimeOfDay.values());
        model.addAttribute("symptoms", Symptom.values());
        return "record_form";
    }

    @PostMapping("/record")
    public String processRecord(@Valid @ModelAttribute Measurement measurement, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("timeOfDays", TimeOfDay.values());
            model.addAttribute("symptoms", Symptom.values());
            return "record_form";
        }
        measurementRepository.save(measurement);
        return "record_success";
    }

    @GetMapping("/measurements")
    public String getAllMeasurements(Model model) {
        List<Measurement> measurements = measurementRepository.findAll();
        model.addAttribute("measurements", measurements);
        return "measurements";
    }

    @GetMapping("/")
    public String redirectToMeasurements() {
        return "redirect:/measurements";
    }
}
