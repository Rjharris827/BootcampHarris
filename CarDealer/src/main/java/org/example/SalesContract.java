package org.example;

    public class SalesContract extends Contract {
        private boolean finance;

        public SalesContract(String date, String customerName, String customerEmail, Vehicle vehicle, boolean finance) {
            super(date, customerName, customerEmail, vehicle);
            this.finance = finance;
        }

        public boolean isFinance() { return finance; }

        @Override
        public double getTotalPrice() {
            double price = getVehicle().getPrice();
            double tax = price * 0.05;
            double recordingFee = 100;
            double processingFee = price < 10000 ? 295 : 495;
            return price + tax + recordingFee + processingFee;
        }

        @Override
        public double getMonthlyPayment() {
            if (!finance) return 0.0;

            double amount = getTotalPrice();
            int months = amount >= 10000 ? 48 : 24;
            double rate = amount >= 10000 ? 0.0425 : 0.0525;
            return (amount * (1 + rate)) / months;
        }
    }

<<<<<<< HEAD
=======

>>>>>>> b6ed75a5b93d20965ef1c5a5f6c927d2505167d8
