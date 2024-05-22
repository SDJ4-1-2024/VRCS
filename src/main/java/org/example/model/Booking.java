package org.example.model;

import org.example.model.client.Client;
import org.example.model.vehicle.Vehicle;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {
    private LocalDate startDate;
    private LocalDate endDate;
    private Vehicle vehicle;
    private Client client;

    public Booking(LocalDate startDate, LocalDate endDate, Vehicle vehicle, Client client) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.vehicle = vehicle;
        this.client = client;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Client getClient() {
        return client;
    }

    public boolean isOngoing() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return currentDateTime.isAfter(startDate.atTime(10, 0)) && currentDateTime.isBefore(endDate.atTime(9, 0));
    }
}
