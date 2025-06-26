package com.pluralsight.dealership.dao;

import com.pluralsight.dealership.models.SalesContract;
import com.pluralsight.dealership.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SalesContractDAOImpl implements SalesContractDAO {

    private static final String INSERT_SQL = "INSERT INTO sales_contracts " +
            "(contract_date, customer_name, customer_email, vehicle_vin, tax, recording_fee, processing_fee, total_price, finance, monthly_payment) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public boolean saveSalesContract(SalesContract contract) throws Exception {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setDate(1, java.sql.Date.valueOf(formatDate(contract.getDate())));
            ps.setString(2, contract.getCustomerName());
            ps.setString(3, contract.getCustomerEmail());
            ps.setInt(4, contract.getVehicle().getVin());

            double price = contract.getVehicle().getPrice();
            double tax = price * 0.05;
            double recordingFee = 100.00;
            double processingFee = price < 10000 ? 295.00 : 495.00;

            ps.setDouble(5, tax);
            ps.setDouble(6, recordingFee);
            ps.setDouble(7, processingFee);
            ps.setDouble(8, contract.getTotalPrice());
            ps.setBoolean(9, contract.isFinance());
            ps.setDouble(10, contract.getMonthlyPayment());

            return ps.executeUpdate() == 1;
        }
    }

    // Converts YYYYMMDD string to java.sql.Date format yyyy-mm-dd
    private String formatDate(String date) {
        return date.substring(0,4) + "-" + date.substring(4,6) + "-" + date.substring(6,8);
    }
}