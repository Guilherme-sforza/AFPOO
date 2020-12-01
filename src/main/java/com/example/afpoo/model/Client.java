package com.example.afpoo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Client {
    private int code;
    private String name;
    private String address;
    private String cpf;

    @JsonIgnore
    private ArrayList<Booking> bookings = new ArrayList<Booking>();


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean addBooking(Booking booking) {
        return bookings.add(booking);
    }

}
