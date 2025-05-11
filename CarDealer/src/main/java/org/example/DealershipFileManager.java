package org.example;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DealershipFileManager {
    private final String filePath = "inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String header = reader.readLine(); // Line 1: dealership info
            if (header == null) {
                System.out.println("Inventory file is empty or missing header.");
                return null;
            }

            String[] parts = header.split("\\|");
            if (parts.length < 3) {
                System.out.println("Dealership information is incomplete in file.");
                return null;
            }

            dealership = new Dealership(parts[0], parts[1], parts[2]);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split("\\|");
                if (fields.length < 8) continue; // skip invalid lines

                int vin = Integer.parseInt(fields[0]);
                int year = Integer.parseInt(fields[1]);
                String make = fields[2];
                String model = fields[3];
                String type = fields[4];
                String color = fields[5];
                int odometer = Integer.parseInt(fields[6]);
                double price = Double.parseDouble(fields[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, type, odometer, price , color);
                dealership.addVehicle(vehicle);
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing vehicle data: " + e.getMessage());
        }

        return dealership;
    }

    /**
     * Saves the dealership and its inventory to the file.
     */
    public void saveDealership(Dealership dealership) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            // Write dealership info
            writer.printf("%s|%s|%s%n", dealership.getName(), dealership.getAddress(), dealership.getPhone());

            // Write each vehicle
            List<Vehicle> vehicles = dealership.getAllVehicles();
            for (Vehicle v : vehicles) {
                writer.printf("%d|%d|%s|%s|%s|%s|%d|%.2f%n",
                        v.getVin(), v.getYear(), v.getMake(), v.getModel(),
                        v.getVehicleType(), v.getColor(), v.getOdometer(), v.getPrice());
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}