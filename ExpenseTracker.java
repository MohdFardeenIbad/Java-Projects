import java.util.*;

public class ExpenseTracker {
    static class Expense {
        int id;
        String date;
        double amount;
        String category;
        String description;

        public Expense(int id, String date, double amount, String category, String description) {
            this.id = id;
            this.date = date;
            this.amount = amount;
            this.category = category;
            this.description = description;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Date: " + date + ", Amount: " + amount +
                   ", Category: " + category + ", Description: " + description;
        }
    }

    private static List<Expense> expenses = new ArrayList<>();
    private static int idCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Generate Report");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addExpense(scanner);
                case 2 -> viewExpenses();
                case 3 -> generateReport();
                case 4 -> {
                    System.out.println("Exiting Expense Tracker. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addExpense(Scanner scanner) {
        System.out.print("Enter date (yyyy-mm-dd): ");
        String date = scanner.nextLine();
        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter category (e.g., Food, Travel): ");
        String category = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        expenses.add(new Expense(idCounter++, date, amount, category, description));
        System.out.println("Expense added successfully!");
    }

    private static void viewExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to display.");
            return;
        }
        System.out.println("\nList of Expenses:");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    private static void generateReport() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses to generate a report.");
            return;
        }
        System.out.println("\nCategory-wise Expense Report:");
        Set<String> categories = new HashSet<>();
        for (Expense expense : expenses) {
            categories.add(expense.category);
        }

        for (String category : categories) {
            double total = 0;
            for (Expense expense : expenses) {
                if (expense.category.equals(category)) {
                    total += expense.amount;
                }
            }
            System.out.println(category + ": " + total);
        }
    }
}

