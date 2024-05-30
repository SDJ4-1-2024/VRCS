package org.example.model.vehicle;

public class VanBuilder {
    private String make;
    private String brand;
    private String registrationPlate;
    private VehicleType vehicleType;
    private Integer pricePerDay;
    private int trunkSpaceHeight;
    private int trunkSpaceWidth;
    private int carryingCapacity;
    private int hp;

    public VanBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    public VanBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public VanBuilder setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
        return this;
    }

    public VanBuilder setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public VanBuilder setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    public VanBuilder setTrunkSpaceHeight(int trunkSpaceHeight) {
        this.trunkSpaceHeight = trunkSpaceHeight;
        return this;
    }

    public VanBuilder setTrunkSpaceWidth(int trunkSpaceWidth) {
        this.trunkSpaceWidth = trunkSpaceWidth;
        return this;
    }

    public VanBuilder setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        return this;
    }

    public VanBuilder setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public Van createVan() {
        return new Van(make, brand, registrationPlate, vehicleType, pricePerDay, trunkSpaceHeight, trunkSpaceWidth, carryingCapacity, hp);
    }
}