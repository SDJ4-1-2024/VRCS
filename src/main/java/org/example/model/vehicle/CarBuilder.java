package org.example.model.vehicle;

public class CarBuilder {
    private String make;
    private String brand;
    private String registrationPlate;
    private VehicleType vehicleType;
    private Integer pricePerDay;
    private int numberOfSeats;
    private int trunkCapacity;
    private int hp;

    public CarBuilder setMake(String make) {
        this.make = make;
        return this;
    }

    public CarBuilder setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public CarBuilder setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
        return this;
    }

    public CarBuilder setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public CarBuilder setPricePerDay(Integer pricePerDay) {
        this.pricePerDay = pricePerDay;
        return this;
    }

    public CarBuilder setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
        return this;
    }

    public CarBuilder setTrunkCapacity(int trunkCapacity) {
        this.trunkCapacity = trunkCapacity;
        return this;
    }

    public CarBuilder setHp(int hp) {
        this.hp = hp;
        return this;
    }

    public Car createCar() {
        return new Car(make, brand, registrationPlate, vehicleType, pricePerDay, numberOfSeats, trunkCapacity, hp);
    }
}