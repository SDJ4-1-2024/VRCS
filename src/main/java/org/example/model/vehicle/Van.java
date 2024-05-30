package org.example.model.vehicle;

public class Van extends Vehicle {
    private int trunkSpaceHeight;
    private int trunkSpaceWidth;
    private int carryingCapacity;
    private int hp;

    public Van(String make, String brand, String registrationPlate, VehicleType vehicleType, Integer pricePerDay, int trunkSpaceHeight, int trunkSpaceWidth, int carryingCapacity, int hp) {
        super(make, brand, registrationPlate, vehicleType, pricePerDay);
        this.trunkSpaceHeight = trunkSpaceHeight;
        this.trunkSpaceWidth = trunkSpaceWidth;
        this.carryingCapacity = carryingCapacity;
        this.hp = hp;
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
