package com.example.afpoo.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Vehicle {
    private int code;
    private String model;
    private double value;

	@JsonIgnore
    private ArrayList<Booking> bookings = new ArrayList<Booking>();

	public int getCode() {
		return code;
    }
	public void setCode(int code) {
		this.code = code;
	}
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

	public boolean addBooking(Booking booking) {
        return bookings.add(booking);
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}
	
}
