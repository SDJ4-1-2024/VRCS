package org.example.model.vechicle;

import org.example.model.Client;

public abstract class Vehicle {
    private VehicleType vehicleType;
    private String registrationPlate;

    public abstract boolean isRented();

    public abstract void rent(Client client);
}
