public class CheckingAccountWithInterest extends CheckingAccount {

    private   double interest = 0.02;

    public CheckingAccountWithInterest(int accountNumber, double balance,BankBranch bankBranch, int noofChecks, double monthlyFee) {
        super(accountNumber, balance, bankBranch, noofChecks, monthlyFee);
        this.interest= 0.02;
    }



    public CheckingAccountWithInterest() {
    }

    @Override
    public String toString() {
        return "CheckingAccountWithInterest{} " + super.toString();
    }

    public double getInterest() {
        return interest;
    }
}
