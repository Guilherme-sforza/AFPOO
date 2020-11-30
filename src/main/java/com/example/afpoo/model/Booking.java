package com.example.afpoo.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Booking {
    private int code;
    private int codeClient;
    private int codeVehicle;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime endDate;
    private double totalCost;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCodeClient() {
        return codeClient;
    }

    public void setCodeClient(int codeClient) {
        this.codeClient = codeClient;
    }

    public int getCodeVehicle() {
        return codeVehicle;
    }

    public void setCodeVehicle(int codeVehicle) {
        this.codeVehicle = codeVehicle;
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

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    

}
