package com.example.peakflow_app.controller;

import com.example.peakflow_app.model.Measurement;
import com.example.peakflow_app.model.Symptom;
import com.example.peakflow_app.model.TimeOfDay;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
public class MeasurementController {
    @GetMapping("/record")
    public String showRecordForm(Model model) {
        model.addAttribute("measurement", new Measurement());
        model.addAttribute("timeOfDays", TimeOfDay.values());
        model.addAttribute("symptoms", Symptom.values());
        return "record_form"; // Имя Thymeleaf-шаблона для отображения формы
    }

    @PostMapping("/record")
    public String processRecord(@Valid @ModelAttribute Measurement measurement, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("timeOfDays", TimeOfDay.values());
            model.addAttribute("symptoms", Symptom.values());
            return "record_form";
        }
        // Временно выводим полученные данные в консоль
        System.out.println("Получены следующие данные измерения:");
        System.out.println("Дата: " + measurement.getDate());
        System.out.println("Время суток: " + measurement.getTimeOfDay());
        System.out.println("Значение пикфлоу: " + measurement.getPeakFlow());
        System.out.println("Симптомы: " + measurement.getDailySymptoms());

        // Здесь позже будет логика сохранения в базу данных

        return "record_success"; // Имя Thymeleaf-шаблона для отображения страницы успеха
    }

    @GetMapping("/current-time")
    @ResponseBody
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "<p> Текущее время: " + now.format(formatter) + "</p>";
    }
}
