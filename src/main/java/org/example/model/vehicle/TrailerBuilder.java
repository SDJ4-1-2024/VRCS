package org.example.model.vehicle;

public class TrailerBuilder {
    private String make;
    private String brand;
    private String registrationPlate;
    private VehicleType vehicleType;
    private Integer pricePerDay;
    private int trunkSpaceHeight;
    private int trunkSpaceWidth;
    private int carryingCapacity;

    public TrailerBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    public TrailerBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public TrailerBuilder setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
        return this;
    }

    public TrailerBuilder setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public TrailerBuilder setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    public TrailerBuilder setTrunkSpaceHeight(int trunkSpaceHeight) {
        this.trunkSpaceHeight = trunkSpaceHeight;
        return this;
    }

    public TrailerBuilder setTrunkSpaceWidth(int trunkSpaceWidth) {
        this.trunkSpaceWidth = trunkSpaceWidth;
        return this;
    }

    public TrailerBuilder setCarryingCapacity(int carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
        return this;
    }

    public Trailer createTrailer() {
        return new Trailer(make, brand, registrationPlate, vehicleType, pricePerDay, trunkSpaceHeight, trunkSpaceWidth, carryingCapacity);
    }
}