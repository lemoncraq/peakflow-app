package com.example.peakflow_app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "measurements")
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Дата измерения обязательна для заполнения.")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Время суток обязательно для выбора.")
    private TimeOfDay timeOfDay;

    @NotNull(message = "Значение пикфлоу обязательно для заполнения.")
    @Min(value = 50, message = "Значение пикфлоу должно быть не меньше 50.")
    @Max(value = 800, message = "Значение пикфлоу должно быть не больше 800.")
    private Integer peakFlow;

    @ElementCollection(targetClass = Symptom.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "measurement_symptoms", joinColumns = @JoinColumn(name = "measurement_id"))
    @Column(name = "symptom")
    private Set<Symptom> dailySymptoms;

    public Measurement() {
    }

    public Measurement(
            LocalDate date,
            TimeOfDay timeOfDay,
            Integer peakFlow,
            Set<Symptom> dailySymptoms) {
        this.date = date;
        this.timeOfDay = timeOfDay;
        this.peakFlow = peakFlow;
        this. dailySymptoms = dailySymptoms;
    }


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public Integer getPeakFlow() {
        return peakFlow;
    }

    public void setPeakFlow(Integer peakFlow) {
        this.peakFlow = peakFlow;
    }

    public Set<Symptom> getDailySymptoms() {
        return dailySymptoms;
    }

    public void setDailySymptoms(Set<Symptom> dailySymptoms) {
        this.dailySymptoms = dailySymptoms;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
