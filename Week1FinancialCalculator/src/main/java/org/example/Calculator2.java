package org.example;
import java.util.Scanner;


public class Calculator2{
    public static void main(String[] args){
            Scanner scanner = new Scanner(System.in);
                System.out.println("What is the intial deposit amount");
                double principal = scanner.nextDouble();
                System.out.println("Show me the annual interest rate");
                double annualRatePercent = scanner.nextDouble();
                System.out.println("What is the number of year money is invested for?");
                double years = scanner.nextDouble();
                double r = (annualRatePercent/365);
                scanner.nextInt();
                int n = 365;

                //FV = P(1 + r/n)^(n*t)


            double futureValue = principal * Math.pow((1+r/n), n * years);
            double totalInterest = futureValue - principal;

        System.out.printf("Future Value (FV): $%.2f, Total Interest Earned: $%.2f", futureValue, totalInterest);








        }
    }
