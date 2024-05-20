package org.example.model.vehicle;

public class Car extends Vehicle {
    private int numberOfSeats;
    private int trunkCapacity;
    private int hp;

    public Car(String make, String brand, String registrationPlate, VehicleType vehicleType, Integer pricePerDay, int numberOfSeats, int trunkCapacity, int hp) {
        super(make, brand, registrationPlate, vehicleType, pricePerDay);
        this.numberOfSeats = numberOfSeats;
        this.trunkCapacity = trunkCapacity;
        this.hp = hp;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getTrunkCapacity() {
        return trunkCapacity;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public boolean isRented() {
        return false;
    }

    @Override
    public boolean isBooked() {
        return false;
    }
}
