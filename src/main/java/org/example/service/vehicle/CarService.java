package org.example.service.vehicle;

import org.example.model.vehicle.Car;
import org.example.repository.vehicle.CarRepository;

import java.time.LocalDate;
import java.util.List;

public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
}
