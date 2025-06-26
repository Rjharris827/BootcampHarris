package org.example;


import java.io.FileWriter;
import java.io.IOException;

public class ContractFileManager {

    public void saveContract(Contract contract) {
        try (FileWriter writer = new FileWriter("contracts.csv", true)) {
            String contractType;
            String details;

            if (contract instanceof SalesContract) {
                SalesContract sale = (SalesContract) contract;
                contractType = "SALE";

                double tax = sale.getVehicle().getPrice() * 0.05;
                double recordingFee = 100.00;
                double processingFee = sale.getVehicle().getPrice() < 10000 ? 295.00 : 495.00;

                details = contractType + "|" +
                        contract.getDate() + "|" +
                        contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" +
                        sale.getVehicle().getVin() + "|" +
                        sale.getVehicle().getYear() + "|" +
                        sale.getVehicle().getMake() + "|" +
                        sale.getVehicle().getModel() + "|" +
                        sale.getVehicle().getVehicleType() + "|" +
                        sale.getVehicle().getColor() + "|" +
                        sale.getVehicle().getOdometer() + "|" +
                        sale.getVehicle().getPrice() + "|" +
                        tax + "|" +
                        recordingFee + "|" +
                        processingFee + "|" +
                        sale.getTotalPrice() + "|" +
                        (sale.isFinance() ? "YES" : "NO") + "|" +
                        sale.getMonthlyPayment();

            } else if (contract instanceof LeaseContract) {
                LeaseContract lease = (LeaseContract) contract;
                contractType = "LEASE";

                double expectedValue = lease.getVehicle().getPrice() * 0.5;
                double leaseFee = lease.getVehicle().getPrice() * 0.07;

                details = contractType + "|" +
                        contract.getDate() + "|" +
                        contract.getCustomerName() + "|" +
                        contract.getCustomerEmail() + "|" +
                        lease.getVehicle().getVin() + "|" +
                        lease.getVehicle().getYear() + "|" +
                        lease.getVehicle().getMake() + "|" +
                        lease.getVehicle().getModel() + "|" +
                        lease.getVehicle().getVehicleType() + "|" +
                        lease.getVehicle().getColor() + "|" +
                        lease.getVehicle().getOdometer() + "|" +
                        lease.getVehicle().getPrice() + "|" +
                        expectedValue + "|" +
                        leaseFee + "|" +
                        lease.getTotalPrice() + "|" +
                        lease.getMonthlyPayment();

            } else {
                System.out.println("Unknown contract type.");
                return;
            }

            writer.write(details + "\n");

        } catch (IOException e) {
            System.out.println("Error writing to contracts file: " + e.getMessage());
        }
    }
}

