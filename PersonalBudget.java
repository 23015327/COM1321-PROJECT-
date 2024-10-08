package PersonalBudget;



import java.util.Scanner;
abstract class Expense {

    
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

    public double calculateMonthlyRepayment() {
        double loanAmount = purchasePrice - deposit;
        double monthlyRate = interestRate / 100 / 12;
        return (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }
}

public class PersonalBudget {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

      
        
        System.out.print("Enter your gross monthly income: ");
        double grossIncome = scanner.nextDouble();

        System.out.print("Enter estimated monthly tax deducted: ");
        double taxDeducted = scanner.nextDouble();

        System.out.print("Enter estimated monthly expenditures (Groceries, Water, Travel, Cell phone, Other): ");
        double groceries = scanner.nextDouble();
        double waterAndLights = scanner.nextDouble();
        double travelCosts = scanner.nextDouble();
        double cellPhone = scanner.nextDouble();
        double otherExpenses = scanner.nextDouble();

        
        
        double totalExpenses = groceries + waterAndLights + travelCosts + cellPhone + otherExpenses;

 
        
        System.out.print("Do you want to rent or buy a property? (Enter 'rent' or 'buy'): ");
        String choice = scanner.next();

        if (choice.equalsIgnoreCase("rent")) {
            System.out.print("Enter monthly rental amount: ");
            double rentalAmount = scanner.nextDouble();
            totalExpenses += rentalAmount;
        } else if (choice.equalsIgnoreCase("buy")) {
            System.out.print("Enter purchase price of the property: ");
            double purchasePrice = scanner.nextDouble();
            System.out.print("Enter total deposit: ");
            double deposit = scanner.nextDouble();
            System.out.print("Enter interest rate (percentage): ");
            double interestRate = scanner.nextDouble();
            System.out.print("Enter number of months to repay (240-360): ");
            int months = scanner.nextInt();

            HomeLoan homeLoan = new HomeLoan(purchasePrice, deposit, interestRate, months);
            double monthlyRepayment = homeLoan.calculateMonthlyRepayment();
            totalExpenses += monthlyRepayment;

           
            
            if (monthlyRepayment > (grossIncome / 3)) {
                System.out.println("Alert: Home loan repayment is unlikely to be approved.");
            }
        }

        
        double availableMoney = grossIncome - (taxDeducted + totalExpenses);
        System.out.println("Available monthly money after deductions: " + availableMoney);
        
        scanner.close();
    }
}
   

