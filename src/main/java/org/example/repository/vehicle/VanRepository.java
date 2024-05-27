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
        String query = "SELECT * FROM van where vehicle_id=" + id;
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
}
