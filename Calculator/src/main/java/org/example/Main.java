package org.example;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
       System.out.print("What is your number?");
       double num1 = scanner.nextDouble();

        System.out.print("What is your 2nd number?");
        double num2 = scanner.nextDouble();


        System.out.print("Whats your 3rd number?");
        double num3 = scanner.nextDouble();

            System.out.print("Whats your 4th number?");
            double num4 = scanner.nextDouble();

        double result = num1 + num2 + num3 + num4;

                System.out.println("The result is " + result);

        scanner.close();


    }
}