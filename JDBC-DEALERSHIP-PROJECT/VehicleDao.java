package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleDAO {
    List<Vehicle> getAllVehicles() throws Exception;
    List<Vehicle> getVehiclesByPriceRange(double min, double max) throws Exception;
    List<Vehicle> getVehiclesByMakeModel(String make, String model) throws Exception;
    List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) throws Exception;
    List<Vehicle> getVehiclesByColor(String color) throws Exception;
    List<Vehicle> getVehiclesByMileageRange(int min, int max) throws Exception;
    List<Vehicle> getVehiclesByType(String type) throws Exception;


    Optional<Vehicle> getVehicleByVin(int vin) throws Exception;

    boolean addVehicle(Vehicle vehicle) throws Exception;
    boolean removeVehicle(int vin) throws Exception;
}
