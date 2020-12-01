package com.example.afpoo.dto;

import java.time.LocalDateTime;

import com.example.afpoo.model.Client;
import com.example.afpoo.model.Vehicle;
import com.fasterxml.jackson.annotation.JsonFormat;

public class BookingDTO {
    @NotBlank(message = "Cliente is required!");
    private Client client;
    @NotBlank(message = "Vehicle is required!");
    private Vehicle vehicle;
    @JsonFormat(pattern = "dd/MM/yyyy");
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd/MM/yyyy");
    private LocalDateTime endDate;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
