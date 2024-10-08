


package PersonalBudget2;



import java.util.Scanner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


abstract class Expense {
    public abstract double calculateMonthlyCost();
}



class HomeLoan extends Expense {
    private double purchasePrice;
    private double deposit;
    private double interestRate;
    private int months;

    public HomeLoan(double purchasePrice, double deposit, double interestRate, int months) {
        this.purchasePrice = purchasePrice;
        this.deposit = deposit;
        this.interestRate = interestRate;
        this.months = months;
    }

    @Override
    public double calculateMonthlyCost() {
        double loanAmount = purchasePrice - deposit;
        double monthlyInterestRate = interestRate / 100 / 12;
        return (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));
    }
}



class Vehicle extends Expense {
    private double purchasePrice;
    private double deposit;
    private double interestRate;
    private double insurancePremium;
    private int months;

    public Vehicle(double purchasePrice, double deposit, double interestRate, double insurancePremium) {
        this.purchasePrice = purchasePrice;
        this.deposit = deposit;
        this.interestRate = interestRate;
        this.insurancePremium = insurancePremium;
        this.months = 60;
    }

    @Override
    public double calculateMonthlyCost() {
        double loanAmount = purchasePrice - deposit;
        double monthlyInterestRate = interestRate / 100 / 12;
        double loanRepayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -months));
        return loanRepayment + insurancePremium;
    }
}


public class PersonalBudget2{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double totalIncome, totalExpenses = 0;

        
        System.out.print("Enter your gross monthly income: ");
        totalIncome = scanner.nextDouble();

        System.out.print("Enter estimated monthly tax deducted: ");
        double taxDeducted = scanner.nextDouble();

        System.out.print("Enter estimated monthly expenses for groceries: ");
        double groceries = scanner.nextDouble();

        System.out.print("Enter estimated monthly expenses for water and lights: ");
        double waterAndLights = scanner.nextDouble();

        System.out.print("Enter estimated monthly expenses for travel: ");
        double travel = scanner.nextDouble();

        System.out.print("Enter estimated monthly expenses for cell phone: ");
        double cellPhone = scanner.nextDouble();

        
        totalExpenses = taxDeducted + groceries + waterAndLights + travel + cellPhone;

       
        System.out.print("Do you want to rent or buy a property? (Enter 'rent' or 'buy'): ");
        String accommodationChoice = scanner.next();

        if (accommodationChoice.equalsIgnoreCase("buy")) {
            System.out.print("Enter purchase price of the property: ");
            double purchasePrice = scanner.nextDouble();

            System.out.print("Enter total deposit: ");
            double deposit = scanner.nextDouble();

            System.out.print("Enter interest rate (percentage): ");
            double interestRate = scanner.nextDouble();

            System.out.print("Enter number of months to repay (240-360): ");
            int months = scanner.nextInt();

            HomeLoan homeLoan = new HomeLoan(purchasePrice, deposit, interestRate, months);
            double homeLoanRepayment = homeLoan.calculateMonthlyCost();
            totalExpenses += homeLoanRepayment;

            if (homeLoanRepayment > totalIncome / 3) {
                System.out.println("Warning: Home loan repayment is more than one-third of your income.");
            }
        } else if (accommodationChoice.equalsIgnoreCase("rent")) {
            System.out.print("Enter monthly rental amount: ");
            double rentAmount = scanner.nextDouble();
            totalExpenses += rentAmount;
        }

        
        System.out.print("Do you want to buy a vehicle? (yes or no): ");
        String vehicleChoice = scanner.next();

        if (vehicleChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter purchase price of the vehicle: ");
            double vehiclePrice = scanner.nextDouble();

            System.out.print("Enter total deposit for the vehicle: ");
            double vehicleDeposit = scanner.nextDouble();

            System.out.print("Enter interest rate for the vehicle (percentage): ");
            double vehicleInterestRate = scanner.nextDouble();

            System.out.print("Enter estimated insurance premium: ");
            double insurancePremium = scanner.nextDouble();

            Vehicle vehicle = new Vehicle(vehiclePrice, vehicleDeposit, vehicleInterestRate, insurancePremium);
            double vehicleCost = vehicle.calculateMonthlyCost();
            totalExpenses += vehicleCost;

            if (totalExpenses > totalIncome * 0.75) {
                System.out.println("Warning: Total expenses exceed 75% of your income.");
            }
        }

       
        double availableMoney = totalIncome - totalExpenses;
        System.out.println("Total expenses: " + totalExpenses);
        System.out.println("Available money after expenses: " + availableMoney);

        
        ArrayList<Expense> expensesList = new ArrayList<>();
        if (accommodationChoice.equalsIgnoreCase("buy")) {
            expensesList.add(new HomeLoan(0, 0, 0, 0));
        }
        if (accommodationChoice.equalsIgnoreCase("rent")) {
            expensesList.add(new Vehicle(0, 0, 0, 0));
        }
        

        Collections.sort(expensesList, new Comparator<Expense>() {
            @Override
            public int compare(Expense e1, Expense e2) {
                return Double.compare(e2.calculateMonthlyCost(), e1.calculateMonthlyCost());
            }
        });

        System.out.println("Expenses in descending order:");
        for (Expense expense : expensesList) {
            System.out.println(expense.calculateMonthlyCost());
        }

        scanner.close();
    }
}
