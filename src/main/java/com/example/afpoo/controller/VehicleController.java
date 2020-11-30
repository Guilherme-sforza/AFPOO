package com.example.afpoo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.example.afpoo.dto.VehicleDTO;
import com.example.afpoo.model.Vehicle;
import com.example.afpoo.service.VehicleService;

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

@RestController
@RequestMapping("/vehicles")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping()
    public List<Vehicle> getVehicles() {
        return vehicleService.getAllVehicles();
    }
    
    @GetMapping("/{code}")
    public ResponseEntity<Vehicle> getVehicleByCode(@PathVariable int code) {
        Vehicle vehicle = vehicleService.getVehicleByCode(code);
        return ResponseEntity.ok(vehicle);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> remove(@PathVariable int code) {
        vehicleService.removeByCode(code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping()
    public ResponseEntity<Vehicle> save(@RequestBody VehicleDTO vehicleDTO, HttpServletRequest request,
            UriComponentsBuilder builder

    ) {

        Vehicle vehicle = vehicleService.fromDTO(vehicleDTO);
        Vehicle newVehicle = vehicleService.save(vehicle);
        UriComponents uriComponents = builder.path(request.getRequestURI() + "/" + newVehicle.getCode()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{code}")
    public ResponseEntity<Vehicle> alter(@RequestBody VehicleDTO vehicleDTO, @PathVariable int code) {
        Vehicle vehicle = vehicleService.fromDTO(vehicleDTO);
        vehicle.setCode(code);
        vehicle = vehicleService.update(vehicle);
        return ResponseEntity.ok(vehicle);
    }
}
