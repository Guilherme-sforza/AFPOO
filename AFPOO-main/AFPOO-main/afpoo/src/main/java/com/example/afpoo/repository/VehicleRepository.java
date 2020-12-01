package com.example.afpoo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.afpoo.model.Vehicle;

import org.springframework.stereotype.Component;

@Component
public class VehicleRepository {

    private List<Vehicle> vehicles;
	private int nextCode;

    @PostConstruct
	public void createVehicles(){
		Vehicle v1 = new Vehicle();
		Vehicle v2 = new Vehicle();
		Vehicle v3 = new Vehicle();

		v1.setCode(1);
		v1.setModel("Lada Vesta");
		v1.setValue(20000);
		

		v2.setCode(2);
		v2.setModel("Lada Granta");
		v2.setValue(30000);
		

		v3.setCode(3);
		v3.setModel("Kia Rio");
		v3.setValue(25000);
		

		vehicles = new ArrayList<Vehicle>();
        vehicles.add(v1);
        vehicles.add(v2);
		vehicles.add(v3);
		
		nextCode = 4;
	}

	public List<Vehicle> getAllClients() {
		return vehicles;
	}

	public Optional<Vehicle> getVehicleByCode(int code) {
		for (Vehicle aux : vehicles) {
            if (aux.getCode() == code) {
                return Optional.of(aux);
            }
        }
        return Optional.empty();
	}

	public void remove(Vehicle vehicleByCode) {
        vehicles.remove(vehicleByCode);
	}

	public Vehicle save(Vehicle vehicle) {
		vehicle.setCode(nextCode++);
        vehicles.add(vehicle);
        return vehicle;
	}

	public Vehicle update(Vehicle vehicle) {
		Vehicle aux = getVehicleByCode(vehicle.getCode()).get();

        if(aux != null){
            aux.setModel(vehicle.getModel());
			aux.setValue(vehicle.getValue());
        }
        return aux;
	}
	
	
}
