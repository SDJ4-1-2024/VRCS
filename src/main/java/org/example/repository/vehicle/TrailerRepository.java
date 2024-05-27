package org.example.repository.vehicle;

import org.example.model.vehicle.Trailer;
import org.example.model.vehicle.TrailerBuilder;
import org.example.model.vehicle.VehicleType;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class TrailerRepository {
    public Optional<Trailer> prepareTrailerById(int id, String make, String brand, String registrationPlate, int pricePerDay) {

        String query = "SELECT * FROM vans where vehicle_id=" + id;
        Optional<Trailer> trailer = Optional.empty();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int trunkSpaceHeight = rs.getInt("trunk_space_height");
                int trunkSpaceWidth = rs.getInt("trunk_space_width");
                int carryingCapacity = rs.getInt("carrying_capacity");
                return Optional.ofNullable(new TrailerBuilder().setMake(make).setBrand(brand).setRegistrationPlate(registrationPlate).setVehicleType(VehicleType.TRAILER)
                        .setPricePerDay(pricePerDay).setTrunkSpaceHeight(trunkSpaceHeight).setTrunkSpaceWidth(trunkSpaceWidth).setCarryingCapacity(carryingCapacity)
                        .createTrailer());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trailer;
    }
}
