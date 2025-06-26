package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.Vehicle;
import com.pluralsight.dealership.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDAOImpl implements VehicleDAO {

    @Override
    public List<Vehicle> getAllVehicles() throws Exception {
        String sql = "SELECT * FROM vehicles";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                vehicles.add(extractVehicle(rs));
            }
            return vehicles;
        }
    }

    @Override
    public List<Vehicle> getVehiclesByPriceRange(double min, double max) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, min);
            ps.setDouble(2, max);
            ResultSet rs = ps.executeQuery();

            List<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                vehicles.add(extractVehicle(rs));
            }
            return vehicles;
        }
    }

    @Override
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE LOWER(make) = LOWER(?) AND LOWER(model) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, make);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();

            List<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                vehicles.add(extractVehicle(rs));
            }
            return vehicles;
        }
    }

    @Override
    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, minYear);
            ps.setInt(2, maxYear);
            ResultSet rs = ps.executeQuery();

            List<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                vehicles.add(extractVehicle(rs));
            }
            return vehicles;
        }
    }

    @Override
    public List<Vehicle> getVehiclesByColor(String color) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE LOWER(color) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, color);
            ResultSet rs = ps.executeQuery();

            List<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                vehicles.add(extractVehicle(rs));
            }
            return vehicles;
        }
    }

    @Override
    public List<Vehicle> getVehiclesByMileageRange(int min, int max) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE odometer BETWEEN ? AND ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, min);
            ps.setInt(2, max);
            ResultSet rs = ps.executeQuery();

            List<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                vehicles.add(extractVehicle(rs));
            }
            return vehicles;
        }
    }

    @Override
    public List<Vehicle> getVehiclesByType(String type) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE LOWER(vehicle_type) = LOWER(?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, type);
            ResultSet rs = ps.executeQuery();

            List<Vehicle> vehicles = new ArrayList<>();
            while (rs.next()) {
                vehicles.add(extractVehicle(rs));
            }
            return vehicles;
        }
    }

    @Override
    public Optional<Vehicle> getVehicleByVin(int vin) throws Exception {
        String sql = "SELECT * FROM vehicles WHERE vin = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vin);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(extractVehicle(rs));
            }
            return Optional.empty();
        }
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) throws Exception {
        String sql = "INSERT INTO vehicles (vin, year, make, model, vehicle_type, color, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vehicle.getVin());
            ps.setInt(2, vehicle.getYear());
            ps.setString(3, vehicle.getMake());
            ps.setString(4, vehicle.getModel());
            ps.setString(5, vehicle.getVehicleType());
            ps.setString(6, vehicle.getColor());
            ps.setInt(7, vehicle.getOdometer());
            ps.setDouble(8, vehicle.getPrice());

            return ps.executeUpdate() == 1;
        }
    }

    @Override
    public boolean removeVehicle(int vin) throws Exception {
        String sql = "DELETE FROM vehicles WHERE vin = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, vin);
            return ps.executeUpdate() == 1;
        }
    }

    private Vehicle extractVehicle(ResultSet rs) throws SQLException {
        return new Vehicle(
                rs.getInt("vin"),
                rs.getInt("year"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getString("color"),
                rs.getInt("odometer"),
                rs.getDouble("price"),
                rs.getString("vehicle_type")
        );
    }
}
