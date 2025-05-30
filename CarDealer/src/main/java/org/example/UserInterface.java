package org.example;


import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;
    private DealershipFileManager fileManager;
    private Scanner scanner = new Scanner(System.in);
    private ContractFileManager manager = new ContractFileManager();

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
                case 10 -> processSellOrLeaseVehicle();
                case 99 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void init() {
        fileManager = new DealershipFileManager("src/main/resources/inventory.csv");
        dealership = fileManager.getDealership();
    }

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
                10 - Sell or Lease a vehicle
                99 - Quit
                Enter your choice:""");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found.");
            return;
        }
        vehicles.forEach(System.out::println);
    }

    private void processAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    private void processGetByPrice() {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByPriceRange(min, max));
    }

    private void processGetByMakeModel() {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYear() {
        System.out.print("Min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max year: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByYearRange(min, max));
    }

    private void processGetByColor() {
        System.out.print("Color: ");
        String color = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileage() {
        System.out.print("Min mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max mileage: ");
        int max = Integer.parseInt(scanner.nextLine());
        displayVehicles(dealership.getVehiclesByMileageRange(min, max));
    }

    private void processGetByType() {
        System.out.print("Type (car, truck, SUV, van): ");
        String type = scanner.nextLine();
        displayVehicles(dealership.getVehiclesByType(type));
    }

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

        Vehicle vehicle = new Vehicle(vin, year, make, model, type, odometer, price, color);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added successfully!");
    }

    private void processRemoveVehicle() {
        System.out.print("Enter VIN of the vehicle to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        dealership.removeVehicle(vin);
        fileManager.saveDealership(dealership);

        System.out.println("Vehicle removed successfully!");
    }

    private void processSellOrLeaseVehicle() {
        System.out.print("Enter VIN of vehicle to sell or lease: ");
        int vin = Integer.parseInt(scanner.nextLine());


        Vehicle vehicle = dealership.getVehicleByVin(vin);
        if (vehicle == null) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.print("Enter contract date (YYYYMMDD): ");
        String date = scanner.nextLine();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();

        System.out.print("Is this a Sale or Lease? (S/L): ");
        String type = scanner.nextLine();

        Contract contract;

        if (type.equalsIgnoreCase("S")) {
            System.out.print("Would the customer like to finance? (yes/no): ");
            boolean finance = scanner.nextLine().equalsIgnoreCase("yes");
            contract = new SalesContract(date, name, email, vehicle, finance);

        } else if (type.equalsIgnoreCase("L")) {
            if (vehicle.getYear() < 2022) {
                System.out.println("Cannot lease a vehicle older than 3 years.");
                return;
            }
            contract = new LeaseContract(date, name, email, vehicle);

        } else {
            System.out.println("Invalid contract type.");
            return;
        }

        manager.saveContract(contract);
        dealership.removeVehicle(vin);
        fileManager.saveDealership(dealership);

        System.out.println("Contract saved. Vehicle removed from inventory.");
    }
}