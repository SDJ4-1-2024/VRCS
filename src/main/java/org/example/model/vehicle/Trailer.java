package org.example.model.vehicle;

public class Trailer extends Vehicle {
    private int trunkSpaceHeight;
    private int trunkSpaceWidth;
    private int carryingCapacity;

    public Trailer(String make, String brand, String registrationPlate, VehicleType vehicleType, Integer pricePerDay, int trunkSpaceHeight, int trunkSpaceWidth, int carryingCapacity) {
        super(make, brand, registrationPlate, vehicleType, pricePerDay);
        this.trunkSpaceHeight = trunkSpaceHeight;
        this.trunkSpaceWidth = trunkSpaceWidth;
        this.carryingCapacity = carryingCapacity;
    }

    public int getTrunkSpaceHeight() {
        return trunkSpaceHeight;
    }

    public int getTrunkSpaceWidth() {
        return trunkSpaceWidth;
    }

    public int getCarryingCapacity() {
        return carryingCapacity;
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
