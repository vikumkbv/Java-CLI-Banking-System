//Class for Bank Account
public class Account  {

    private int accountNumber;
    private double balance;
    private BankBranch homeBranch;


    public Account(int accountNumber, double balance, BankBranch homeBranch) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.homeBranch = homeBranch;
    }

    public Account() {
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public double getBalance() {
        return this.balance;
    }

    public BankBranch getHomeBranch() {
        return this.homeBranch;
    }

    public void displayBranch()
    {
        System.out.println(this.homeBranch);
    }

    public void withdraw(double amount) {
        this.balance = this.balance - amount;
    }

    public void deposit(double amount) {
        this.balance = this.balance + amount;
    }

    @Override
    public String toString() {
        return "AccountNumber=" + accountNumber +
                ", balance=" + balance +
                ", homeBranch=" + homeBranch +
                '}';
    }
}
