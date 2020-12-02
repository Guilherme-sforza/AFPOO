package com.example.afpoo.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.afpoo.dto.BookingDTO;
import com.example.afpoo.model.Booking;
import com.example.afpoo.model.Client;
import com.example.afpoo.model.Vehicle;
import com.example.afpoo.repository.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BookingService {

	@Autowired
    private BookingRepository repository;
	
	@Autowired
	private ClientService clientService;


	public List<Booking> getAllBookings() {
		return repository.getAllBookings();
	}

	public Booking getBookingByCode(int code) {
		Optional<Booking> op = repository.getBookingByCode(code);
         return op.orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Booking not registered"
                   )
                );
	}

	public Booking fromDTO(BookingDTO bookingDTO) {
		Booking booking = new Booking();
		booking.setClient(bookingDTO.getClient());
		booking.setVehicle(bookingDTO.getVehicle());
		booking.setStartDate(bookingDTO.getStartDate());
		booking.setEndDate(bookingDTO.getEndDate());
		return booking;
	}


	public Booking save(Booking booking, int idClient, Vehicle vehicle) {
        Client client = clientService.getClientByCode(idClient);
		
		booking.setClient(client);
		booking.setVehicle(vehicle);

		client.addBooking(booking);
		vehicle.addBooking(booking);

		LocalDateTime start = booking.getStartDate();
		LocalDateTime end = booking.getEndDate();
		
		booking.setTotalCost(vehicle.getValue()*ChronoUnit.DAYS.between(start, end));
		//booking.setTotalCost(ChronoUnit.DAYS.between(start, end));
        return repository.save(booking);
	}


	public void dateCheck(@Valid Booking booking, Vehicle vehicle) {
		String message;
		LocalDateTime start = booking.getStartDate();
		LocalDateTime end = booking.getEndDate();
		ArrayList<Booking> bookingsList = vehicle.getBookings();
		
		if(!(bookingsList.isEmpty()))
		for(int i = 0; i < bookingsList.size(); i++){
			Booking aux = bookingsList.get(i);
			if(	!(start.isBefore(aux.getStartDate()) && end.isBefore(aux.getStartDate()) || 
				start.isAfter(aux.getEndDate()) && end.isAfter(aux.getEndDate())))
				Optional.empty().orElseThrow( () -> 
                   new ResponseStatusException(
						HttpStatus.METHOD_NOT_ALLOWED,
						"Booking period conflicts with existing booking; "+
						"Conflicting booking Code: " + aux.getCode() + "; " +
						"Conflicting booking period: " +
						"startDate: " + aux.getStartDate() + "; " +
						"endDate: " + aux.getEndDate()
                   )
				); 
		}

		if(start.getDayOfWeek().equals(DayOfWeek.SUNDAY))
			message = "A booking's starting date must not be on a sunday.";
		else if(end.getDayOfWeek().equals(DayOfWeek.SUNDAY))
			message = "A booking's due date must not be on a sunday.";
		else if(start.getDayOfYear() == (LocalDateTime.now().getDayOfYear()))
			message = "A booking's starting date must be different from current date";
		else if(end.compareTo(start) <= 0)
			message = "A booking's due date must be after starting date";
		else
			return;
			

		Optional.empty().orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.METHOD_NOT_ALLOWED,message
                   )
				); 
		
	}

	
    
}
