package org.example.model;

import org.example.model.vechicle.Vehicle;

public class Rent {

    private Vehicle vehicle;
    private Client client;

    public Rent(Vehicle vehicle, Client client){
        this.vehicle = vehicle;
        this.client = client;
    }

}
