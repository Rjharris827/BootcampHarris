package org.example;
import java.util.Scanner;
public class Calculator3 {
    public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       System.out.println("What is the monthly payout amount?");
       double Pmt = scanner.nextDouble();
        System.out.println("What is the monthly interest rate?");
        double k = scanner.nextDouble();
        double r = (k/12) / 100;
        System.out.println("How many years to pay out?");
        double payOutYears = scanner.nextDouble();
        double n = (payOutYears *12);

        //PV = PMT × [(1 - (1 + r)^(-n)) / r]

        double pv = Pmt * (1 - Math.pow(1 + r, -n)) / r;
        System.out.printf("Present Value (PV) of the Annuity: $%.2f", pv);



    }
}

