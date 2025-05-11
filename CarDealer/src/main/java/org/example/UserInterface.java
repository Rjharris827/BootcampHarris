package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager fileManager;
    private Scanner scanner = new Scanner(System.in);

    // Main method to display the menu and handle user input
    public void display() {
        init();

        while (true) {
            showMenu();
            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> processGetByPrice();
                case 2 -> processGetByMakeModel();
                case 3 -> processGetByYear();
                case 4 -> processGetByColor();
                case 5 -> processGetByMileage();
                case 6 -> processGetByType();
                case 7 -> processAllVehiclesRequest();
                case 8 -> processAddVehicle();
                case 9 -> processRemoveVehicle();
                case 99 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // Initializes the DealershipFileManager and loads the dealership data from file
    private void init() {
        fileManager = new DealershipFileManager("src/main/resources/inventory.csv");
        dealership = fileManager.getDealership();
    }

    // Displays the menu with available options
    private void showMenu() {
        System.out.println("""
                \n--- Dealership Menu ---
                1 - Find vehicles within a price range
                2 - Find vehicles by make/model
                3 - Find vehicles by year range
                4 - Find vehicles by color
                5 - Find vehicles by mileage range
                6 - Find vehicles by type (car, truck, SUV, van)
                7 - List ALL vehicles
                8 - Add a vehicle
                9 - Remove a vehicle
                99 - Quit
                Enter your choice:""");
    }

    // Helper method to display a list of vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        vehicles.forEach(System.out::println);
    }

    // Displays all vehicles in the dealership
    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    // Finds vehicles based on price range
    private void processGetByPrice() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByPriceRange(min, max));
    }

    // Finds vehicles based on make and model
    private void processGetByMakeModel() {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    // Finds vehicles based on year range
    private void processGetByYear() {
        System.out.print("Min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max year: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByYearRange(min, max));
    }

    // Finds vehicles based on color
    private void processGetByColor() {
        System.out.print("Color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    // Finds vehicles based on mileage range
    private void processGetByMileage() {
        System.out.print("Min mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max mileage: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByMileageRange(min, max));
    }

    // Finds vehicles based on vehicle type (car, truck, SUV, van)
    private void processGetByType() {
        System.out.print("Type (car, truck, SUV, van): ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

    // Adds a new vehicle to the dealership
    private void processAddVehicle() {
        System.out.print("Enter VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Make: ");
        String make = scanner.nextLine();
        System.out.print("Enter Model: ");
        String model = scanner.nextLine();
        System.out.print("Enter Type: ");
        String type = scanner.nextLine();
        System.out.print("Enter Color: ");
        String color = scanner.nextLine();
        System.out.print("Enter Odometer: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        // Create the new vehicle and add it to the dealership
        Vehicle vehicle = new Vehicle(vin, year, make, model, type, odometer, price, color);
        dealership.addVehicle(vehicle);

        // Save the dealership data to the file
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully!");
    }

    // Removes a vehicle from the dealership based on VIN
    private void processRemoveVehicle() {
        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        dealership.removeVehicle(vin);

        // Save the updated dealership data to the file
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle removed successfully!");
    }
}



