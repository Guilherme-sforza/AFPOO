package com.example.afpoo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.afpoo.model.Booking;

import org.springframework.stereotype.Component;

@Component
public class BookingRepository {

    private List<Booking> bookings = new ArrayList<Booking>();;
    private int nextCode = 1; 

    

    public List<Booking> getAllBookings() {
		return bookings;
	}

	public Optional<Booking> getBookingByCode(int code) {
		for (Booking aux : bookings) {
            if (aux.getCode() == code) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
	}

	public Booking save(Booking booking) {
		booking.setCode(nextCode++);
        bookings.add(booking);
        return booking;
	}

    
}
