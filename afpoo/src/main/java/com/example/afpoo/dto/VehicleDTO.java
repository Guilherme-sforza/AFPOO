package com.example.afpoo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class VehicleDTO {
    
    @NotBlank(message = "Model is required")
    private String model;
    @Min(value = 1, message = "Value must not be less than 1")
    private double value;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
