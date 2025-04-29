import java.util.Scanner;

class BankAccount {
    String accountHolder;
    String accountNumber;
    double balance;

    BankAccount(String holder, String accNumber) {
        this.accountHolder = holder;
        this.accountNumber = accNumber;
        this.balance = 0.0;
    }

    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposited ₹" + amount);
        } else {
            System.out.println("❌ Invalid amount!");
        }
    }

    void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid amount!");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient funds!");
        } else {
            balance -= amount;
            System.out.println("✅ Withdrawn ₹" + amount);
        }
    }

    void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    void accountDetails() {
        System.out.println("👤 Name: " + accountHolder);
        System.out.println("🏦 Account No: " + accountNumber);
        System.out.println("💰 Balance: ₹" + balance);
    }
}

public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount account = null;
        int choice;

        do {
            System.out.println("\n--- Bank Account System ---");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter account number: ");
                    String accNo = sc.nextLine();
                    account = new BankAccount(name, accNo);
                    System.out.println("✅ Account created successfully!");
                }
                case 2 -> {
                    if (account == null) {
                        System.out.println("⚠️ Create an account first!");
                    } else {
                        System.out.print("Enter amount to deposit: ");
                        double amount = sc.nextDouble();
                        account.deposit(amount);
                    }
                }
                case 3 -> {
                    if (account == null) {
                        System.out.println("⚠️ Create an account first!");
                    } else {
                        System.out.print("Enter amount to withdraw: ");
                        double amount = sc.nextDouble();
                        account.withdraw(amount);
                    }
                }
                case 4 -> {
                    if (account == null) {
                        System.out.println("⚠️ Create an account first!");
                    } else {
                        account.checkBalance();
                    }
                }
                case 5 -> {
                    if (account == null) {
                        System.out.println("⚠️ Create an account first!");
                    } else {
                        account.accountDetails();
                    }
                }
                case 6 -> System.out.println("👋 Exiting...");
                default -> System.out.println("❌ Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}
