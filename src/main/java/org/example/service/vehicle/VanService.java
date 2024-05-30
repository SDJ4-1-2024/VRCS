package org.example.service.vehicle;

import org.example.model.vehicle.Car;
import org.example.model.vehicle.Van;
import org.example.model.vehicle.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class VanService {

    public VanService() {
    }

    public List<Van> castVehiclesToVans(List<Vehicle> vehicles){
        return vehicles.stream()
                .filter(vehicle -> vehicle instanceof Van)
                .map(vehicle -> (Van) vehicle)
                .collect(Collectors.toList());
    }
}
