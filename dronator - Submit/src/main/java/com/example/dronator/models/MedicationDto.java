package com.example.dronator.models;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;

public class MedicationDto {

    private String medicationId;
    @Pattern(regexp="[\\w\\-]+", message = "Value does not match pattern")
    private String name;
    @PositiveOrZero
    private Double weight;
    @Pattern(regexp="^[A-Z0-9]+(?:_[A-Z0-9]+)*$", message = "Value does not mathe pattern")
    private String code;

    public String getMedicationId() {
        return medicationId;
    }

    public void setMedicationId(String medicationId) {
        this.medicationId = medicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
