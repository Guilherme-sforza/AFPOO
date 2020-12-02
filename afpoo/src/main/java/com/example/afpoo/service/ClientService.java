package com.example.afpoo.service;

import java.util.List;
import java.util.Optional;

import com.example.afpoo.dto.ClientDTO;
import com.example.afpoo.model.Client;
import com.example.afpoo.repository.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

	public List<Client> getAllClients() {
		return repository.getAllClients();
	}

    public Client getClientByCode(int code) {
        Optional<Client> op = repository.getClientByCode(code);
         return op.orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Client not registered"
                   )
                );
    }
    
    public void removeByCode(int code) {
        Client aux = repository.getClientByCode(code).get();
        if(aux.getBookings().isEmpty())
        {
            repository.remove(aux); 
            return;
        }
        Optional.empty().orElseThrow( () ->
            new ResponseStatusException( 
                HttpStatus.METHOD_NOT_ALLOWED,"Client has bookings")
        );
    }


	public Client save(Client client) {
		return repository.save(client);
	}

	public Client fromDTO(ClientDTO clientDTO) {
		Client client = new Client();
        client.setAddress(clientDTO.getAddress());
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCPF());
        return client;
	}

	public Client update(Client client) {
		getClientByCode(client.getCode());
        return repository.update(client);
	}



    
}
