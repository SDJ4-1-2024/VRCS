package org.example.service.vehicle;

import org.example.model.vehicle.Car;
import org.example.model.vehicle.Vehicle;
import org.example.repository.vehicle.CarRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class CarService {

    public CarService() {
    }

    public List<Car> castVehiclesToCars(List<Vehicle> vehicles){
        return vehicles.stream()
                .filter(vehicle -> vehicle instanceof Car)
                .map(vehicle -> (Car) vehicle)
                .collect(Collectors.toList());
    }
}
