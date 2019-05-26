public class CheckingAccount extends Account {

	private int noofChecks;
	private double monthlyFee;

	public CheckingAccount(int accountNumber, double balance,  BankBranch bankBranch, int noofChecks, double monthlyFee) {
		super(accountNumber, balance,  bankBranch);
		this.noofChecks = noofChecks;
		this.monthlyFee = monthlyFee;
	}

	public CheckingAccount() {

	}

	@Override
	public String toString() {
		return "CheckingAccount{" +
				"noofChecks=" + noofChecks +
				", monthlyFee=" + monthlyFee +
				"} " + super.toString();
	}

	public int getNoofChecks() {
		return noofChecks;
	}

	public void setNoofChecks(int noofChecks) {
		this.noofChecks = noofChecks;
	}

	public double getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}


}