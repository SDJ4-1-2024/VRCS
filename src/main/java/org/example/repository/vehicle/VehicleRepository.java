package org.example.repository.vehicle;

import javafx.print.Collation;
import org.example.model.vehicle.*;
import org.example.util.DatabaseUtil;
import org.example.util.LocalDateConverter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VehicleRepository {
    CarRepository carRepository;
    VanRepository vanRepository;
    TrailerRepository trailerRepository;

    public VehicleRepository() {
        this.carRepository = new CarRepository();
        this.vanRepository = new VanRepository();
        this.trailerRepository = new TrailerRepository();
    }

    public Vehicle prepareVehicleById(int id) {
        String query = "SELECT * FROM vehicles where id=" + id;
        Optional<Vehicle> vehicle = Optional.empty();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehicle vehInit = prepareCompleteVehicle(rs);
                if (vehInit != null) return vehInit;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicle.orElseThrow();
    }

    private Vehicle prepareCompleteVehicle(ResultSet rs) throws SQLException {
        Optional<Vehicle> vehicle = Optional.empty();
        int idOb = rs.getInt("id");
        String make = rs.getString("make");
        String brand = rs.getString("brand");
        String registrationPlate = rs.getString("registration_plate");
        String vehicleType = rs.getString("vehicle_type");
        int pricePerDay = rs.getInt("price_per_day");
        if (vehicleType.equals(VehicleType.CAR.name())) {
            Optional<Car> carInit = carRepository.prepareCarById(idOb, make, brand, registrationPlate, pricePerDay);
            return carInit.orElseThrow();
        }
        if (vehicleType.equals(VehicleType.VAN.name())) {
            Optional<Van> vanInit = vanRepository.prepareVanById(idOb, make, brand, registrationPlate, pricePerDay);
            return vanInit.orElseThrow();
        }
        if (vehicleType.equals(VehicleType.TRAILER.name())) {
            Optional<Trailer> trailerInit = trailerRepository.prepareTrailerById(idOb, make, brand, registrationPlate, pricePerDay);
            return trailerInit.orElseThrow();
        }
        return vehicle.orElseThrow();
    }

    public List<Vehicle> loadAvailableVehiclesInTimePeriodRange(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {
        String query = prepareQuery(startDate, endDate, vehicleType);
        List<Vehicle> vehicles = new ArrayList<>(Collections.emptyList());
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vehicle vehicle = (Vehicle) prepareCompleteVehicle(rs);
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    private static String prepareQuery(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {
        Date startDateDB = LocalDateConverter.convertToDatabaseColumn(startDate);
        Date endDateDB = LocalDateConverter.convertToDatabaseColumn(endDate);
        String query = "SELECT * FROM vehicles WHERE vehicle_type = '" + vehicleType + "' AND id NOT IN ("
                + "SELECT vehicle_id FROM bookings WHERE ("
                + "(start_date <= '" + startDateDB + "' AND end_date >= '" + endDateDB + "') "
                + "OR (start_date <= '" + startDateDB + "' AND end_date >= '" + endDateDB + "')"
                + "))";
        return query;
    }

    public List<Car> loadCars() {
        String query = "SELECT * FROM vehicles WHERE vehicle_type ='" + VehicleType.CAR + "'";
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Car car = (Car) prepareCompleteVehicle(rs);
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Van> loadVans() {
        String query = "SELECT * FROM vehicles WHERE vehicle_type ='" + VehicleType.VAN + "'";
        List<Van> vans = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Van van = (Van) prepareCompleteVehicle(rs);
                vans.add(van);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vans;
    }

    public List<Trailer> loadTrailers() {
        String query = "SELECT * FROM vehicles WHERE vehicle_type ='" + VehicleType.TRAILER + "'";
        List<Trailer> trailers = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Trailer trailer = (Trailer) prepareCompleteVehicle(rs);
                trailers.add(trailer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trailers;
    }

    public Optional<Integer> prepareVehicleIdByRegPlate(String registrationPlate) {
        String query = "SELECT id FROM vehicles where registration_plate='" + registrationPlate + "'";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return Optional.of(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public boolean isVehicleAvailable(String registrationPlate) {
        String query = "SELECT 1 " +
                "FROM bookings b " +
                "JOIN vehicles v ON b.vehicle_id = v.id " +
                "WHERE v.registration_plate = ? " + //? placeholder for the argument function -> registrationPlat in this case
                "AND b.end_date >= CURRENT_DATE";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, registrationPlate);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void removeVehicle(String registrationPlate) {
        String deleteVehicleQuery = "DELETE FROM vehicles WHERE id = (SELECT id FROM vehicles WHERE registration_plate = ?) AND NOT EXISTS (SELECT 1 FROM bookings WHERE vehicles.id = bookings.vehicle_id AND bookings.end_date >= CURRENT_DATE)";
        String deleteCarQuery = "DELETE FROM cars WHERE vehicle_id = (SELECT id FROM vehicles WHERE registration_plate = ?) AND NOT EXISTS (SELECT 1 FROM bookings WHERE cars.vehicle_id = bookings.vehicle_id AND bookings.end_date >= CURRENT_DATE)";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement deleteVehicleStmt = conn.prepareStatement(deleteVehicleQuery);
             PreparedStatement deleteCarStmt = conn.prepareStatement(deleteCarQuery)) {

            deleteVehicleStmt.setString(1, registrationPlate);
            deleteCarStmt.setString(1, registrationPlate);

            // Execute the delete statements
            deleteCarStmt.executeUpdate();
            deleteVehicleStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveVehicle(Vehicle vehicle) {
        String queryVehicles = "INSERT INTO vehicles (make, brand, registration_plate, vehicle_type, price_per_day) VALUES (?, ?, ?, ?::vehicle_type, ?)";

        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryVehicles)) {

            preparedStatement.setString(1, vehicle.getMake());
            preparedStatement.setString(2, vehicle.getBrand());
            preparedStatement.setString(3, vehicle.getRegistrationPlate());
            preparedStatement.setString(4, vehicle.getVehicleType().name());
            preparedStatement.setInt(5, vehicle.getPricePerDay());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int vehicleId = prepareVehicleIdByRegPlate(vehicle.getRegistrationPlate()).orElseThrow();
        String vehicleType = vehicle.getVehicleType().name();
        if (vehicleType.equals(VehicleType.CAR.name())) {
            Car car = (Car) vehicle;
            carRepository.saveCar(car, vehicleId);
        }
        if (vehicleType.equals(VehicleType.VAN.name())) {
            Van van = (Van) vehicle;
            vanRepository.saveVan(van, vehicleId);
        }
        if (vehicleType.equals(VehicleType.TRAILER.name())) {
            Trailer trailer = (Trailer) vehicle;
            trailerRepository.saveTrailer(trailer, vehicleId);
        }
    }

}
