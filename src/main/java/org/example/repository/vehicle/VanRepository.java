package org.example.repository.vehicle;

import org.example.model.vehicle.Van;
import org.example.model.vehicle.VanBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class VanRepository {
    public Optional<Van> prepareVanById(int id, String make, String brand, String registrationPlate, int pricePerDay) {
        String query = "SELECT * FROM vans where vehicle_id=" + id;
        Optional<Van> van = Optional.empty();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int trunkSpaceHeight = rs.getInt("trunk_space_height");
                int trunkSpaceWidth = rs.getInt("trunk_space_width");
                int carryingCapacity = rs.getInt("carrying_capacity");
                int hp = rs.getInt("hp");
                return Optional.ofNullable(new VanBuilder().setMake(make).setBrand(brand).setRegistrationPlate(registrationPlate).setVehicleType(VehicleType.VAN)
                        .setPricePerDay(pricePerDay).setTrunkSpaceHeight(trunkSpaceHeight).setTrunkSpaceWidth(trunkSpaceWidth).setCarryingCapacity(carryingCapacity)
                        .setHp(hp).createVan());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return van;
    }

    public void saveVan(Van van, int vehicleId) {
        String query = "INSERT INTO vans (vehicle_id, trunk_space_height, trunk_space_width, carrying_capacity, hp) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vehicleId);
            preparedStatement.setInt(2, van.getTrunkSpaceHeight());
            preparedStatement.setInt(3, van.getTrunkSpaceWidth());
            preparedStatement.setInt(4, van.getCarryingCapacity());
            preparedStatement.setInt(5, van.getHp());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
