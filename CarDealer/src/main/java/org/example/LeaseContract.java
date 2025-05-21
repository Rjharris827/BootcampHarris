package org.example;


    public class LeaseContract extends Contract {
        public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle) {
            super(date, customerName, customerEmail, vehicle);
        }

        @Override
        public double getTotalPrice() {
            double expectedEndingValue = getVehicle().getPrice() * 0.50;
            double leaseFee = getVehicle().getPrice() * 0.07;
            return expectedEndingValue + leaseFee;
        }

        @Override
        public double getMonthlyPayment() {
            double leaseAmount = getTotalPrice();
            return (leaseAmount * 1.04) / 36; // 4% over 36 months
        }
    }

