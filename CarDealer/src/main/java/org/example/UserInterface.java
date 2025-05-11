package org.example;

    import java.util.List;
import java.util.Scanner;

    public class UserInterface {
        private Dealership dealership;
        private Scanner scanner = new Scanner(System.in);

        public void display() {
            init();

            while (true) {
                displayMenu();
                int option;

                try {
                    option = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    continue;
                }

                switch (option) {
                    case 7: processAllVehiclesRequest(); break;
                    case 99:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Option not implemented yet.");
                }
            }
        }

        private void init() {
            DealershipFileManager fileManager = new DealershipFileManager();
            dealership = fileManager.getDealership();
            if (dealership == null) {
                System.out.println("Failed to load dealership. Exiting...");
                System.exit(1);
            }
        }

        private void displayMenu() {
            System.out.println("\nWelcome to " + dealership.getName());
            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");
            System.out.print("Choose an option: ");
        }

        private void processAllVehiclesRequest() {
            List<Vehicle> vehicles = dealership.getAllVehicles();
            displayVehicles(vehicles);
        }

        private void displayVehicles(List<Vehicle> vehicles) {
            if (vehicles.isEmpty()) {
                System.out.println("No vehicles available.");
            } else {
                for (Vehicle v : vehicles) {
                    System.out.println(v);
                }
            }
        }
    }

