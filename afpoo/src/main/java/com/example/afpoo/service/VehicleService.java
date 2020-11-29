package com.example.afpoo.service;

import java.util.List;
import java.util.Optional;

import com.example.afpoo.dto.VehicleDTO;
import com.example.afpoo.model.Vehicle;
import com.example.afpoo.repository.VehicleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class VehicleService {

	@Autowired
    VehicleRepository repository;
	
	public List<Vehicle> getAllVehicles() {
		return repository.getAllClients();
	}

	public Vehicle getVehicleByCode(int code) {
		Optional<Vehicle> op = repository.getVehicleByCode(code);
         return op.orElseThrow( () -> 
                   new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Veiculo nao cadastrado"
                   )
                );
	}

	public void removeByCode(int code) {
		repository.remove(getVehicleByCode(code));
	}

	public Vehicle save(Vehicle vehicle) {
		return repository.save(vehicle);
	}
	
	public Vehicle fromDTO(VehicleDTO vehicleDTO) {
		Vehicle vehicle = new Vehicle();
        vehicle.setModel(vehicleDTO.getModel());
        vehicle.setValue(vehicleDTO.getValue());
        return vehicle;
	}

	public Vehicle update(Vehicle vehicle) {
		getVehicleByCode(vehicle.getCode());
        return repository.update(vehicle);
	}
    
}
