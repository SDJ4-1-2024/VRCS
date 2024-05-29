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

        String query = "SELECT * FROM trailers where vehicle_id=" + id;
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

    public void saveTrailer(Trailer trailer, int vehicleId) {
        String query = "INSERT INTO trailers (vehicle_id, trunk_space_height, trunk_space_width, carrying_capacity) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, vehicleId);
            preparedStatement.setInt(2, trailer.getTrunkSpaceHeight());
            preparedStatement.setInt(3, trailer.getTrunkSpaceWidth());
            preparedStatement.setInt(4, trailer.getCarryingCapacity());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
