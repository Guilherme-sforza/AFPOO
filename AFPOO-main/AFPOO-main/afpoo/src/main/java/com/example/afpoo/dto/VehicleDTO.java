package com.example.afpoo.dto;

public class VehicleDTO {
    
    @NotBlank(message = "Model is required!");
    private String model;
    @NotBlank(message = "Vehicle's value is required!");
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
