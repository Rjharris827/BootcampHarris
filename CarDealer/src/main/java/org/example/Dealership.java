package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    // Info about the dealership
    private String name;
    private String address;
    private String phone;

    // A list of all the vehicles the dealership has
    private List<Vehicle> inventory;

    // When we create a new dealership, we give it a name, address, and phone
    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>(); // start with an empty list of vehicles
    }

    // Add a vehicle to the inventory
    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }

    // Remove a vehicle based on its VIN number
    public void removeVehicle(int vin) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getVin() == vin) {
                inventory.remove(i);
                break;
            }
        }
    }

    // Get all the vehicles we have
    public List<Vehicle> getAllVehicles() {
        return inventory;
    }

    // Find vehicles in a price range
    public List<Vehicle> getVehiclesByPriceRange(double min, double max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getPrice() >= min && v.getPrice() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    // Find vehicles by make and model (like "Ford" and "F-150")
    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getMake().equalsIgnoreCase(make) && v.getModel().equalsIgnoreCase(model)) {
                result.add(v);
            }
        }
        return result;
    }

    // Find vehicles made between two years
    public List<Vehicle> getVehiclesByYearRange(int minYear, int maxYear) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getYear() >= minYear && v.getYear() <= maxYear) {
                result.add(v);
            }
        }
        return result;
    }

    // Find vehicles that are a certain color
    public List<Vehicle> getVehiclesByColor(String color) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getColor().equalsIgnoreCase(color)) {
                result.add(v);
            }
        }
        return result;
    }

    // Find vehicles with mileage between two numbers
    public List<Vehicle> getVehiclesByMileageRange(int min, int max) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getOdometer() >= min && v.getOdometer() <= max) {
                result.add(v);
            }
        }
        return result;
    }

    // Find vehicles by their type (like car, truck, SUV, van)
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVehicleType().equalsIgnoreCase(type)) {
                result.add(v);
            }
        }
        return result;
    }

    public Vehicle getVehicleByVin(int vin) {
        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                return v;
            }
        }
        return null;
    }

    // Get info about the dealership
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }
}