package com.example.afpoo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.afpoo.model.Client;

import org.springframework.stereotype.Component;

@Component
public class ClientRepository {

	private List<Client> clients;
	private int nextCode;

	@PostConstruct
	public void createClients(){
		Client c1 = new Client();
		Client c2 = new Client();
		Client c3 = new Client();

		c1.setCode(1);
		c1.setName("Putin");
		c1.setAddress("Rua Moscow, 1999");
		c1.setCpf("111.111.111-11");

		c2.setCode(2);
		c2.setName("Lenin");
		c2.setAddress("Rua Mencheviques, 1903");
		c2.setCpf("222.222.222-22");

		c3.setCode(3);
		c3.setName("Rasputin");
		c3.setAddress("Rua Ra-Ra-Rasputin, 1916");
		c3.setCpf("333.333.333-33");

		clients = new ArrayList<Client>();
        clients.add(c1);
        clients.add(c2);
		clients.add(c3);
		
		nextCode = 4;
	}

	
	public List<Client> getAllClients() {
		return clients;
	}

	public Optional<Client> getClientByCode(int code) {
		for (Client aux : clients) {
            if (aux.getCode() == code) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
	}

	public void remove(Client clientByCode) {
		clients.remove(clientByCode);
	}


	public Client save(Client client) {
		client.setCode(nextCode++);
        clients.add(client);
        return client;
	}

	public Client update(Client client) {
		Client aux = getClientByCode(client.getCode()).get();

        if(aux != null){
            aux.setAddress(client.getAddress());
			aux.setName(client.getName());
			aux.setCpf(client.getCpf());
        }
        return aux;
	}
    
}
