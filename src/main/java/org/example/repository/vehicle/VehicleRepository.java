package org.example.repository.vehicle;

import org.example.model.vehicle.*;
import org.example.util.DatabaseUtil;
import org.example.util.LocalDateConverter;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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
        return null;
    }

    public List<Car> loadAvailableCarsInTimePeriodRange(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {
        String query = prepareQuery(startDate, endDate, vehicleType);
        List<Car> cars = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Car car = (Car) prepareCompleteVehicle(rs);
                cars.add(car);
            }
            return cars;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public List<Van> loadAvailableVansInTimePeriodRange(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {
        String query = prepareQuery(startDate, endDate, vehicleType);
        List<Van> vans = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Van van = (Van) prepareCompleteVehicle(rs);
                vans.add(van);
            }
            return vans;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vans;
    }

    public List<Trailer> loadAvailableTrailersInTimePeriodRange(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {
        String query = prepareQuery(startDate, endDate, vehicleType);
        List<Trailer> trailers = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Trailer trailer = (Trailer) prepareCompleteVehicle(rs);
                trailers.add(trailer);
            }
            return trailers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trailers;
    }

    private static String prepareQuery(LocalDate startDate, LocalDate endDate, VehicleType vehicleType) {
        Date startDateDB = LocalDateConverter.convertToDatabaseColumn(startDate);
        Date endDateDB = LocalDateConverter.convertToDatabaseColumn(endDate);
        String query = "SELECT * FROM vehicles WHERE vehicle_type = '"+ vehicleType +"' AND id NOT IN ("
                + "SELECT vehicle_id FROM bookings WHERE ("
                + "(start_date <= '"+startDateDB+"' AND end_date >= '"+endDateDB+"') "
                + "OR (start_date <= '"+startDateDB+"' AND end_date >= '"+endDateDB+"')"
                + "))";
        return query;
    }

    public List<Car> loadCars() {
        String query = "SELECT * FROM vehicles WHERE vehicle_type =" + VehicleType.CAR;
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
        String query = "SELECT * FROM vehicles WHERE vehicle_type =" + VehicleType.VAN;
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
        String query = "SELECT * FROM vehicles WHERE vehicle_type =" + VehicleType.TRAILER;
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
}
