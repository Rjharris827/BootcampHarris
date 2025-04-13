package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the principal amount ?");
        double p = scanner.nextDouble();
        System.out.println("What is the interest rate?");
        double k = scanner.nextDouble();
        double r = (k/12) / 100;
        System.out.println("How many years is the loan term?");
        double loanYears = scanner.nextDouble();
        double n = (loanYears *12);

        //M = P[r(1+r)^n] / [(1+r)^n-1]

        double j=1+r;
        double l=Math.pow(j,n);
        double left=(l*r) * p;
        double right=(l-1);
        double monthlyPayments= left/right;
        double interest = (monthlyPayments * n) - p;
        System.out.printf("Monthly bill is $%.2f,total interest due is $%.2f", monthlyPayments, interest);



    }
}
