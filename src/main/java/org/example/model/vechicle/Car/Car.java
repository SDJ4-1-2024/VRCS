package org.example.model.vechicle.Car;

import org.example.model.Client;
import org.example.model.Rent;
import org.example.model.vechicle.Vehicle;
import org.example.model.vechicle.VehicleType;

public class Car extends Vehicle {

    private int numberOfSeats;
    private int trunkCapacity;
    private int hp;
    private CarType carType;

    public Car(VehicleType vehicleType, String regPlate, int numberOfSeats, int trunkCapacity, int hp, CarType carType) {
        super();
        this.numberOfSeats = numberOfSeats;
        this.trunkCapacity = trunkCapacity;
        this.hp = hp;
        this.carType = carType;

    }

    @Override
    public boolean isRented() {
        return false;
    }

    @Override
    public void rent(Client client) {
        System.out.println("N/A");
    }
}
