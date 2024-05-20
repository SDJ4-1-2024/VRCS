package org.example.model.vehicle;

public abstract class Vehicle {

    private String make;
    private String brand;
    private String registrationPlate;
    private VehicleType vehicleType;
    private Integer pricePerDay;

    public Vehicle(String make, String brand, String registrationPlate, VehicleType vehicleType, Integer pricePerDay) {
        this.make = make;
        this.brand = brand;
        this.registrationPlate = registrationPlate;
        this.vehicleType = vehicleType;
        this.pricePerDay = pricePerDay;
    }

    public String getMake() {
        return make;
    }

    public String getBrand() {
        return brand;
    }

    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Integer getPricePerDay() {
        return pricePerDay;
    }

    public abstract boolean isRented();

    public abstract boolean isBooked();
}
