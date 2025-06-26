package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    // File path where the dealership data is stored
    private String filePath;

    // Constructor - when we create the DealershipFileManager, we give it the file path
    public DealershipFileManager(String filePath) {
        this.filePath = filePath;
    }

    // This method loads the data from the file and creates a Dealership with that data
    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // The first line has the dealership info
            String header = reader.readLine();
            if (header == null) {
                System.out.println("The file is empty or missing the dealership info.");
                return null;
            }

            // Split the dealership info into parts: name, address, and phone number
            String[] parts = header.split("\\|");
            if (parts.length < 3) {
                System.out.println("Dealership info is incomplete in the file.");
                return null;
            }

            // Create a new dealership object
            dealership = new Dealership(parts[0], parts[1], parts[2]);


            // Now we need to read all the vehicles (each vehicle is on its own line)
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length < 8) continue; // skip lines that are not valid

                // Get the vehicle details
                int vin = Integer.parseInt(fields[0]);
                int year = Integer.parseInt(fields[1]);
                String make = fields[2];
                String model = fields[3];
                String type = fields[4];
                String color = fields[5];
                int odometer = Integer.parseInt(fields[6]);
                double price = Double.parseDouble(fields[7]);

                // Create a new Vehicle object and add it to the dealership
                Vehicle vehicle = new Vehicle(vin, year, make, model, type, odometer, price, color);
                dealership.addVehicle(vehicle);
            }

        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error in the vehicle data format: " + e.getMessage());
        }

        return dealership;
    }

    // This method saves the current dealership (name, address, phone) and all vehicles to the file
    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write the dealership info on the first line
            writer.printf("%s|%s|%s%n", dealership.getName(), dealership.getAddress(), dealership.getPhone());

            // Write each vehicle on a new line
            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (Vehicle v : vehicles) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
            }

        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}