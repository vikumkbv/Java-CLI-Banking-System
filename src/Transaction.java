import java.util.Scanner;

public class Transaction {

    //  Withdraw Fund
    private static void withdraw(Account acc) {
        Scanner input = new Scanner(System.in);
        double amount = BankAccount_7.doubleValidator(input, "Enter the amount in USD ($): ");
        acc.withdraw(amount);
        System.out.println("Account updated Successfully");
    }

    //  call function withdraw_money()
    static void withdrawCall(Client cli) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        int account = BankAccount_7.intValidator(input, "Enter Account Number: ");
        if (cli.accountExist(account)) {
            Account acc = cli.accountFind(account);
            withdraw(acc);
        } else {
            withdrawCall(cli);
        }
        System.out.println();
        System.out.println("Return the main menu (Y)");
        String enter = input.next();
        if (enter.equals("Y")) {
            BankAccount_7.staffMenu();
        } else {
            BankAccount_7.staffMenu();
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
    //  Deposit Funds
    private static void deposit(Client cli, int acc) {
        if (cli.accountExist(acc)) {
            Scanner input = new Scanner(System.in);
            double amount = BankAccount_7.doubleValidator(input, "Enter the amount in USD ($): ");
            Account acco = cli.accountFind(acc);
            acco.deposit(amount);
            System.out.println("Account updated Successfully");
        } else {
            System.err.println("Account Does Not Exist. Try Again");
        }
    }

    //  call function Deposit Money ()
    static void depositCall(Client cli) {
        System.out.println();
        Scanner input = new Scanner(System.in);
        int account = BankAccount_7.intValidator(input, "Enter Account Number: ");
        if (cli.accountExist(account)) {
//            Account acc=cli.accountFind(Account);
            deposit(cli, account);
        }
        System.out.println();
        System.out.print("Return the main menu (Y)");
        String enter = input.next();
        if (enter.equals("Y")) {
            BankAccount_7.staffMenu();
        } else {
            BankAccount_7.staffMenu();
        }
    }

    //----------------------------------------------------------------------------------------------------------------------
    //money transfer between bank Accounts
    private static void transferCall(int account_from, int account_to, double amount) {
        double i = 10.0; //variable for 10$ limit
        double j = 100000.0; //variable for federal insured limit

        CheckingAccountWithInterest from =Client.accountFind(account_from);//ko meka
        CheckingAccountWithInterest to = Client.accountFind(account_to);

        System.out.println("Are you sure you want to continue transfer ? | Yes = y | No = any key");
        Scanner input = new Scanner(System.in);

        String confirmTrans = input.nextLine();

        if (confirmTrans.equalsIgnoreCase("y")) {
            System.out.println("Transfer authenticate with system");
        } else {
            System.out.println("Transfer stopped !!! \n Thank you for using service !!!");
            BankAccount_7.clientMenu();
        }

        if (from != null && to != null && from.getBalance() > amount) {
            from.withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer Successful !!!");

            //warning msg for low acc balance
            if (i > from.getBalance()) {
                System.err.println("Warning !!! Your Account Balance is Low !!!");
            }
            //warning msg for federal insured limit
            if (to.getBalance() >= j) {
                System.err.println("Warning !!! Transaction Exceed Federally Insured Limit !!!");
            }
            System.out.println("Your Available Balance: " + from.getBalance());
        } else
            //error message to keep first Account(from) balance above or on $0.00
            System.err.println("Sorry !!! Transfer Failed You Don't Have Enough Credits");
    }

    //  call function transferCall()
    static void transfer() {
        Scanner input = new Scanner(System.in);

        Client from = BankAccount_7.clientLogin();
        boolean found = false;
//        Account to_acc= new Account();
        int from_acc = BankAccount_7.intValidator(input, "Enter Account Number: ");;
       // Account from_acco;
        while (!found) {
            if (from.accountExist(from_acc)) {
//                from_acco = from.accountFind(from_acc);
                found = true;
            } else {
                System.out.println("Enter Your Account Number: ");
                from_acc = input.nextInt();
            }
        }

        System.out.println();
        int to = BankAccount_7.intValidator(input, "Enter Account Number of Money Receiver: ");

//        Account to_check = accountFind(to);

        if (from_acc == to) {
            System.err.println("Same Account Number Transfer Can not Continue...\n");
            transfer();
        }

        if (BankAccount_7.accountExist(to)) {
            double amount = BankAccount_7.doubleValidator(input, "Enter the amount in USD ($): ");
            if (amount > 0) {
                transferCall(from_acc, to, amount);
            } else {
                System.err.println("Enter Valid Amount");
                transfer();
            }
        } else {
            System.err.println("Receiver Account Not Found");
            transfer();
        }
        System.out.println();
        System.out.println("Return the main menu (Y)");
        String enter = input.next();
        if (enter.equals("Y")) {
            BankAccount_7.clientMenu();
        } else {
            BankAccount_7.clientMenu();
        }
    }


}
