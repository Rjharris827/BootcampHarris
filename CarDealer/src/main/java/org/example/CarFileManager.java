package org.example;


import java.io.*;
import java.util.List;

public class CarFileManager {
    // Path to the inventory file
    private final String FILE_PATH = "data/inventory.csv";

    // Loads the dealership from the file
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String dealershipInfo = reader.readLine();

            // Make sure the file is not empty
            if (dealershipInfo == null || dealershipInfo.isEmpty()) {
                System.out.println("Error: File is empty or missing dealership info.");
                return null;
            }

            // First line: dealership name, address, phone
            String[] dealershipData = dealershipInfo.split("\\|");

            // Check if we have exactly 3 fields
            if (dealershipData.length != 3) {
                System.out.println("Error: Dealership info is incomplete.");
                return null;
            }

            dealership = new Dealership(dealershipData[0], dealershipData[1], dealershipData[2]);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                // Check if the line has exactly 8 fields
                if (data.length != 8) {
                    System.out.println("Skipping invalid vehicle entry: " + line);
                    continue;
                }

                try {
                    // Create a new Vehicle and add it to the dealership
                    Vehicle vehicle = new Vehicle(
                            Integer.parseInt(data[0]), // VIN
                            Integer.parseInt(data[1]), // Year
                            data[2],                   // Make
                            data[3],                   // Model
                            data[4],                   // Color
                            Integer.parseInt(data[5]), // Odometer
                            Double.parseDouble(data[6]), // Price
                            data[7]                    // Type
                    );
                    dealership.addVehicle(vehicle);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing vehicle data: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

        return dealership;
    }

    // Saves the current dealership back to the file
    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            // Write dealership info on the first line
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            // Write each vehicle on a new line
            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (Vehicle v : vehicles) {
                writer.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                        v.getColor() + "|" + v.getMileage() + "|" + v.getPrice() + "|" + v.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }
}
