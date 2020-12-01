package com.example.afpoo.service;

import java.util.List;
import java.util.Optional;

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

	@Autowired
	private VehicleService vehicleService;

	public List<Booking> getAllBookings() {
		return repository.getAllBookings();
	}

	public Booking getBookingByCode(int code) {
		Optional<Booking> op = repository.getBookingByCode(code);
         return op.orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Reserva nao cadastrada"
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


	public Booking save(Booking booking, int idClient, int idVehicle) {
        Client client = clientService.getClientByCode(idClient);
		Vehicle vehicle = vehicleService.getVehicleByCode(idVehicle);
		
		booking.setClient(client);
		booking.setVehicle(vehicle);

		client.addBooking(booking);
		vehicle.addBooking(booking);

        return repository.save(booking);
	}

	
    
}
