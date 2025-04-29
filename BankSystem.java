import java.util.*;

class Account {
    String id;
    String name;
    double balance;

    Account(String id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid deposit amount.");
            return;
        }
        balance += amount;
        System.out.println("✅ Deposited. New Balance: ₹" + balance);
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdraw amount.");
        } else if (amount > balance) {
            System.out.println("❌ Not enough balance.");
        } else {
            balance -= amount;
            System.out.println("✅ Withdrawn. New Balance: ₹" + balance);
        }
    }

    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Balance: ₹" + balance;
    }
}

public class BankSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Bank Account System ---");
            System.out.println("1. Add Account");
            System.out.println("2. View All Accounts");
            System.out.println("3. Search by Account ID");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid number: ");
                sc.next(); // clear invalid
            }
            choice = sc.nextInt();
            sc.nextLine(); // flush

            switch (choice) {
                case 1 -> addAccount();
                case 2 -> viewAccounts();
                case 3 -> searchAccount();
                case 4 -> depositToAccount();
                case 5 -> withdrawFromAccount();
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 6);
    }

    static void addAccount() {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        double balance = readDouble("Enter initial balance: ");
        accounts.add(new Account(id, name, balance));
        System.out.println("✅ Account created.");
    }

    static void viewAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account acc : accounts) {
                System.out.println(acc);
            }
        }
    }

    static void searchAccount() {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine();
        for (Account acc : accounts) {
            if (acc.id.equals(id)) {
                System.out.println(acc);
                return;
            }
        }
        System.out.println("❌ Account not found.");
    }

    static void depositToAccount() {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine();
        Account acc = findAccount(id);
        if (acc != null) {
            double amt = readDouble("Enter amount to deposit: ");
            acc.deposit(amt);
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    static void withdrawFromAccount() {
        System.out.print("Enter Account ID: ");
        String id = sc.nextLine();
        Account acc = findAccount(id);
        if (acc != null) {
            double amt = readDouble("Enter amount to withdraw: ");
            acc.withdraw(amt);
        } else {
            System.out.println("❌ Account not found.");
        }
    }

    static Account findAccount(String id) {
        for (Account acc : accounts) {
            if (acc.id.equals(id))
                return acc;
        }
        return null;
    }

    static double readDouble(String msg) {
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
            System.out.print("Enter a valid number: ");
            sc.next(); // clear invalid
        }
        return sc.nextDouble();
    }
}
