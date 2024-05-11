package org.example.model.vechicle;

import org.example.model.Client;
import org.example.model.Rent;

public class Car extends Vehicle {

    private int numberOfSeats;
    private int trunkCapacity;
    private int hp;

    public Car(VehicleType vehicleType, String regPlate, int numberOfSeats, int trunkCapacity, int hp) {
        super();
        this.numberOfSeats = numberOfSeats;
        this.trunkCapacity = trunkCapacity;
        this.hp = hp;
    }

    @Override
    public boolean isRented() {
        return false;
    }

    @Override
    public Rent rent(Client client) {
        return null;
    }
}
