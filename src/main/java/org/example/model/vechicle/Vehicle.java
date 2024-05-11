package org.example.model.vechicle;

import org.example.model.Client;
import org.example.model.Rent;

public abstract class Vehicle {
    private VehicleType vehicleType;
    private String registrationPlate;

    public abstract boolean isRented();

    public abstract Rent rent(Client client);
}
