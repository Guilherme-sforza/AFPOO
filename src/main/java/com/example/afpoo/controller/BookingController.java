package com.example.afpoo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.afpoo.dto.BookingDTO;
import com.example.afpoo.model.Booking;
import com.example.afpoo.service.BookingService;
import com.example.afpoo.service.ClientService;
import com.example.afpoo.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/booking")
public class BookingController {
    
    @Autowired
    private BookingService bookingService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private VehicleService vehicleService;

    @GetMapping()
    public List<Booking> getBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{code}")//listar todas as reservas
    public ResponseEntity<Booking> getBookingByCode(@PathVariable int code) {
        Booking booking = bookingService.getBookingByCode(code);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/client/{code}")//listar as reservas de um cliente

    @PostMapping()
    public ResponseEntity<Booking> save(@RequestBody BookingDTO bookingDTO, HttpServletRequest request,
            UriComponentsBuilder builder
    ) {
        Booking booking = bookingService.fromDTO(bookingDTO);
        Booking newBooking = bookingService.save(booking);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + newBooking.getCode()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }





}

