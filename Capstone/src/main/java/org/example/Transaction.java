package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {

    private String description;
    private String vendor;
    private double amount;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;

    public Transaction(String description, String vendor, double amount, LocalDate appointmentDate, LocalTime appointmentTime) {
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
    }


    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "description='" + description + '\'' +
                ", vendor='" + vendor + '\'' +
                ", amount=" + amount +
                ", appointmentDate=" + appointmentDate +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}
