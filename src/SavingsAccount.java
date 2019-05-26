public class SavingsAccount extends Account {

	private  double interestRate = 0.03;

	public SavingsAccount(int accountNumber, double balance,  BankBranch bankBranch, double interestRate) {
		super(accountNumber, balance,  bankBranch);
		this.interestRate = interestRate;
	}

	public SavingsAccount() {
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
}
