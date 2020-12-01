package com.example.afpoo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.afpoo.dto.ClientDTO;
import com.example.afpoo.model.Booking;
import com.example.afpoo.model.Client;
import com.example.afpoo.service.BookingService;
import com.example.afpoo.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private BookingService bookingService;

    @GetMapping()
    public List<Client> getClients() {
        return clientService.getAllClients();
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Client> getClientByCode(@PathVariable int code) {
        Client client = clientService.getClientByCode(code);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> remove(@PathVariable int code) {
        clientService.removeByCode(code);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Client> att(@RequestBody ClientDTO clientDTO, @PathVariable int code){
        Client client = clientService.fromDTO(clientDTO);
        client.setCode(code);
        client = clientService.update(client);
        return ResponseEntity.ok(client);
    } 

    @GetMapping("/{codigo}")
    public ResponseEntity<Client> getClientByCode(@PathVariable int code){
        Client client = clientService.getClientByCode(code);
        return ResponseEntity.ok(client);
    }


    @PostMapping()
    public ResponseEntity<Client> save(@Valid @RequestBody ClientDTO clientDTO, HttpServletRequest request,
            UriComponentsBuilder builder

    ) {

        Client client = clientService.fromDTO(clientDTO);
        Client newClient = clientService.save(client);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + newClient.getCode()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PostMapping("/{idClient}/booking/{idVehicle}")
    public ResponseEntity<Booking> save(@PathVariable int idClient, @PathVariable int idVehicle, @RequestBody Booking booking,
            HttpServletRequest request, UriComponentsBuilder builder

    ) {

        booking = bookingService.save(booking, idClient, idVehicle);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + booking.getCode()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Client> alter(@RequestBody ClientDTO clientDTO, @PathVariable int code) {
        Client client = clientService.fromDTO(clientDTO);
        client.setCode(code);
        client = clientService.update(client);
        return ResponseEntity.ok(client);
    }
    
}
