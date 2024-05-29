package org.example.service.vehicle;

import org.example.model.vehicle.Trailer;
import org.example.model.vehicle.Vehicle;

import java.util.List;
import java.util.stream.Collectors;

public class TrailerService {
    public TrailerService() {
    }

    public List<Trailer> castVehiclesToTrailers(List<Vehicle> vehicles) {
        return vehicles.stream()
                .filter(vehicle -> vehicle instanceof Trailer)
                .map(vehicle -> (Trailer) vehicle)
                .collect(Collectors.toList());
    }
}
