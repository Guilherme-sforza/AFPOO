package com.example.afpoo.controller;

import java.util.List;

import com.example.afpoo.model.Booking;
import com.example.afpoo.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{code}")
    public ResponseEntity<Booking> getBookingByCode(@PathVariable int code) {
        Booking booking = bookingService.getBookingByCode(code);
        return ResponseEntity.ok(booking);
    }




}

