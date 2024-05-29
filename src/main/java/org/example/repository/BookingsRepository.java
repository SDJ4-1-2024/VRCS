package org.example.repository;

import org.example.model.Booking;
import org.example.model.client.Client;
import org.example.model.vehicle.Vehicle;
import org.example.repository.vehicle.VehicleRepository;
import org.example.util.DatabaseUtil;
import org.example.util.LocalDateConverter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookingsRepository {
    private final VehicleRepository vehicleRepository = new VehicleRepository();
    private final ClientRepository clientRepository = new ClientRepository();

    public List<Booking> loadBookings() {
        String query = "SELECT * FROM bookings";
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Date startDateInit = rs.getDate("start_date");
                Date endDateInit = rs.getDate("end_date");
                int vehicleId = rs.getInt("vehicle_id");
                int clientId = rs.getInt("client_id");
                LocalDate startDate = LocalDateConverter.convertToEntityAttribute(startDateInit);
                LocalDate endDate = LocalDateConverter.convertToEntityAttribute(endDateInit);
                Vehicle vehicle = vehicleRepository.prepareVehicleById(vehicleId);
                Client client = clientRepository.prepareClientById(clientId).orElseThrow();
                bookings.add(new Booking(startDate, endDate, vehicle, client));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public List<Booking> loadBookingsByClientId(int clientIdOb) {
        String query = "SELECT * FROM bookings WHERE client_id=?";
        List<Booking> bookings = new ArrayList<>(Collections.emptyList());
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, clientIdOb);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Date startDateInit = rs.getDate("start_date");
                    Date endDateInit = rs.getDate("end_date");
                    int vehicleId = rs.getInt("vehicle_id");
                    int clientId = rs.getInt("client_id");
                    LocalDate startDate = LocalDateConverter.convertToEntityAttribute(startDateInit);
                    LocalDate endDate = LocalDateConverter.convertToEntityAttribute(endDateInit);
                    Vehicle vehicle = vehicleRepository.prepareVehicleById(vehicleId);
                    Client client = clientRepository.prepareClientById(clientId).orElseThrow();
                    bookings.add(new Booking(startDate, endDate, vehicle, client));
                }
                return bookings;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    public Optional<Booking> loadBooking(int bookingId) {
        String query = "SELECT * FROM bookings WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Date startDateInit = rs.getDate("start_date");
                    Date endDateInit = rs.getDate("end_date");
                    int vehicleId = rs.getInt("vehicle_id");
                    int clientId = rs.getInt("client_id");
                    LocalDate startDate = LocalDateConverter.convertToEntityAttribute(startDateInit);
                    LocalDate endDate = LocalDateConverter.convertToEntityAttribute(endDateInit);
                    Vehicle vehicle = vehicleRepository.prepareVehicleById(vehicleId);
                    Client client = clientRepository.prepareClientById(clientId).orElseThrow();
                    return Optional.of(new Booking(startDate, endDate, vehicle, client));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void saveBooking(Date startDate, Date endDate, int carId, int clientId) {
        String query = "INSERT INTO bookings (start_date, end_date, vehicle_id, client_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            stmt.setInt(3, carId);
            stmt.setInt(4, clientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Integer> getBookingId(LocalDate startDate, LocalDate endDate, String phoneNumber, String registrationPlate) {

        String query = "SELECT b.id FROM bookings b JOIN clients c ON b.client_id = c.id JOIN vehicles v ON b.vehicle_id = v.id WHERE b.start_date = ? AND b.end_date = ? AND c.phone_number = ? AND v.registration_plate = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, java.sql.Date.valueOf(startDate));
            stmt.setDate(2, java.sql.Date.valueOf(endDate));
            stmt.setString(3, phoneNumber);
            stmt.setString(4, registrationPlate);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void removeBooking(Date startDate, Date endDate, Integer vehicleId, Integer clientId) {
        String query = "DELETE FROM bookings WHERE start_date = ? AND end_date = ? AND client_id = ? AND vehicle_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setDate(1, startDate);
            stmt.setDate(2, endDate);
            stmt.setInt(3, clientId);
            stmt.setInt(4, vehicleId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

