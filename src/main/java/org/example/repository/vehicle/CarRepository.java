package org.example.repository.vehicle;

import org.example.model.vehicle.Car;
import org.example.model.vehicle.CarBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class CarRepository {

    public CarRepository() {
    }

    public Optional<Car> prepareCarById(int id, String make, String brand, String registrationPlate, int pricePerDay) {
        String query = "SELECT * FROM cars where vehicle_id=" + id;
        Optional<Car> car = Optional.empty();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int numberOfSeats = rs.getInt("number_of_seats");
                int trunkCapacity = rs.getInt("trunk_capacity");
                int hp = rs.getInt("hp");
                return Optional.ofNullable(new CarBuilder().setMake(make).setBrand(brand).setRegistrationPlate(registrationPlate).setVehicleType(VehicleType.CAR)
                        .setPricePerDay(pricePerDay).setNumberOfSeats(numberOfSeats).setTrunkCapacity(trunkCapacity).setHp(hp).createCar());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return car;
    }

    public void saveCar(Car car, int vehicleId) {
        String queryCars = "INSERT INTO cars (vehicle_id, number_of_seats, trunk_capacity, hp) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryCars)) {

            preparedStatement.setInt(1, vehicleId);
            preparedStatement.setInt(2, car.getNumberOfSeats());
            preparedStatement.setInt(3, car.getTrunkCapacity());
            preparedStatement.setInt(4, car.getHp());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
