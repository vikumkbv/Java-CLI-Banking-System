//Import the required libraries

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//----------------------------------------------------------------------------------------------------------------------

public class BankAccount_7 {

    private static String session[] = {"username", "password"};
    private static String staff[][] = {{"Admin", "Admin123"}, {"Manager", "Manager123"}};

    //To store bank Account info
    private static List<Client> Clients = new ArrayList<>();


//----------------------------------------------------------------------------------------------------------------------
    //input validation section

    //int value validator
    static int intValidator(Scanner input, String numValidatorMsg) {
        System.out.println(numValidatorMsg);
        while (!input.hasNextInt()) {
            System.err.println("Not a Number,  Please Enter Number  !!!");
            input.next();
        }
        return input.nextInt();
    }

    //double value validator
    static double doubleValidator(Scanner input, String doubleMsg) {
        System.out.println(doubleMsg);
        while (!input.hasNextDouble()) {
            System.err.println("Not a Number,  Please Enter Number  !!!");
            input.nextDouble();
        }
        return input.nextDouble();
    }

//----------------------------------------------------------------------------------------------------------------------

    //  Welcome Message
    private static void welcome() {
        System.out.println("--------------------------------------------------");
        System.out.println(">>>>Welcome to the InterBank Management System<<<<");
        System.out.println("--------------------------------------------------");
    }

//----------------------------------------------------------------------------------------------------------------------

    //  Main Menu interfaces of InterBank pty
    private static void loginType() {
        welcome();
        System.out.println("---------------------");
        System.out.println("Enter your Login Type");
        System.out.println("---------------------");
        System.out.println("1. Staff Member");
        System.out.println("2. Client");
        System.out.println("3. Exit");
        System.out.println("---------------------");
        System.out.print("Command: ");
        Scanner input = new Scanner(System.in);
        String command = input.next();
        System.out.println();
        switch (command) {
            case "1":
                loginStaff();
                staffMenu();
                break;

            case "2":
                clientMenu();
                break;

            case "3":
                System.out.println("Programme Exist. Thank you for Banking with us !!! ");
                System.exit(0);
                break;
            default:
                System.err.println("Invalid Command, Try again");
                loginType();
                break;
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    //    Take system users (staff) info;
    private static void loginStaff() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Username: ");
        String username = sc.next();
        System.out.println("Password: ");
        String password = sc.next();
        System.out.println();
        session[0] = username;
        session[1] = password;
    }

//----------------------------------------------------------------------------------------------------------------------

    //  check given info
    private static Boolean checker() {
        String data[] = session;
        Boolean result = false;
        for (int i = 0; i < data.length; i++) {
            if ((data[0].equals(staff[i][0])) && (data[1].equals(staff[i][1]))) {
                result = true;
                break;
            }
        }
        return result;
    }

//----------------------------------------------------------------------------------------------------------------------


//----------------------------------------------------------------------------------------------------------------------

    //  Staff Menu interface
    static void staffMenu() {
        if (checker()) {
            System.out.println("=====================================");
            System.out.println(">>>>>>>>> Available Options <<<<<<<<<");
            System.out.println("=====================================");
            System.out.println("1. Open Checking Account With Interest");
            System.out.println("2. View Account");
            System.out.println("3. Withdraw money");
            System.out.println("4. Deposit Money");
            System.out.println("5. Report Generate");
            System.out.println("6. Logout From Staff");
            System.out.println("7. Back to Main Login");
            System.out.println("8. Exit");
            System.out.println("=====================================");
            System.out.println("command : ");
            Scanner input = new Scanner(System.in);
            String command = input.next();
            switch (command) {
                case "1":
                    enterAccountData(createUser());
                    break;
                case "2":
                    view();
                    break;
                case "3":
                    Transaction.withdrawCall(getClient());
                    break;
                case "4":
                    Transaction.depositCall(getClient());
                    break;
                case "5":
                    produceReport();
                    break;
                case "6":
                    loginStaff();
                    staffMenu();
                    break;
                case "7":
                    loginType();
                    break;
                case "8":
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid Command !!! Try Again.");
                    staffMenu();
            }
        } else {
            System.err.println("Login Details are Invalid !!! Try Again" + "\n");
            loginStaff();
            staffMenu();
        }
    }
//----------------------------------------------------------------------------------------------------------------------

    //  Client Menu interface
    static void clientMenu() {
        System.out.println("---------------------");
        System.out.println(">>Available Options<<");
        System.out.println("---------------------");
        System.out.println("1. View Account info");
        System.out.println("2. Money Transfer");
        System.out.println("3. Logout");
        System.out.println("---------------------");
        System.out.print("Command: ");
        Scanner input = new Scanner(System.in);
        String command = input.next();
        System.out.println();
        switch (command) {
            case "1":
                viewinfo();
                clientMenu();
                break;
            case "2":
                Transaction.transfer();
                break;
            case "3":
                loginType();
                break;
            default:
                System.out.println("Invalid Command !!! Try Again");
                clientMenu();
        }
    }

//----------------------------------------------------------------------------------------------------------------------
    private static boolean clientExist(String id) {
        boolean clientExist = true;
        if (!Clients.isEmpty()) {
            int i = 0;
            for (Client client_data : Clients) {
                i += 1;
                if (client_data.getIdnum().equals(id)) {
                    i -= 1;
                    break;
                }
            }
            if (i == Clients.size()) {
                clientExist = false;
            }
        } else {
            clientExist = false;
        }
        return clientExist;
    }

//----------------------------------------------------------------------------------------------------------------------
    //Search required account_id from the users array and return the Account;
    private static Client clientFind(String id) {

        Client found = new Client("", "", 0);
        if (!Clients.isEmpty()) {
            for (Client client_data : Clients) {
                if (client_data.getIdnum().equals(id)) {
                    found = client_data;
                }
            }
        }
        return found;
    }

//----------------------------------------------------------------------------------------------------------------------
    static boolean accountExist(int acc) {
        for (Client current_c : Clients) {
            if (current_c.accountExist(acc)) {
                return true;
            }
        }
        return false;
    }

    private static Client clientFind1(Account acc) {
        for (Client current_c : Clients) {
            if (current_c.accountExist(acc.getAccountNumber())) {
                return current_c;
            }
        }
        return null;
    }

    private static Client getClient() {
        System.out.println("Enter the Client id");
        Scanner input = new Scanner(System.in);
        String id = input.nextLine();
        Client cli = new Client("", "", 0);
        if (clientExist(id)) {
            cli = clientFind(id);
        } else {
            System.out.println("Client Not Found in the System");
            getClient();
        }
        return cli;
    }

//----------------------------------------------------------------------------------------------------------------------

    // Create a new User
    private static String createUser() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter Name: ");
        String name = input.nextLine();

        System.out.println("Enter User Account Password: ");
        String uPassword = input.nextLine();

        System.out.println("Enter the NIC number: ");
        String idnum = input.nextLine();

        int tp = intValidator(input, "Enter the Phone Number: ");

        Client user = new Client(name, idnum, tp, uPassword);
        Clients.add(user);
        return user.getName();
    }


    // Create a new Bank Account
    private static int noOfAcc = 1;

    private static void enterAccountData(String username) {

        Scanner input = new Scanner(System.in);

        int bsbnumber = intValidator(input, "Enter BSB Number : ");

        int postcode = intValidator(input, "Enter Post Code : ");

        System.out.println("Enter Branch Address : ");
        String addresss = input.next();
        BankBranch bankBranchObject = new BankBranch(bsbnumber, addresss, postcode);

        int accountNumberGenerated;
        accountNumberGenerated = 1000 + (int) (Math.random() * ((9999 - 1000) + 1));
        System.out.println("Account Number " + noOfAcc + " set to : " + accountNumberGenerated);

        while (!(accountNumberGenerated >= 1000 && accountNumberGenerated <= 9999)) {

            System.err.println("Account Number Out of Range. Enter value between 1000 - 9999");
            accountNumberGenerated = 1000 + (int) (Math.random() * ((9999 - 1000) + 1));
            System.out.println("Account Number " + noOfAcc + " set to : " + accountNumberGenerated);
        }

        double opbalance;
        opbalance = doubleValidator(input, "Enter Opening Balance: ");

        while ((!(opbalance > 0.0) && !(opbalance <= 100000))) {
            System.err.println("Balance should not be Negative or Over 100000");
            doubleValidator(input, "Enter Opening Balance: ");
        }

        int noofChecks = BankAccount_7.intValidator(input, "Enter Number of checks allowed per month : ");

        double monthlyFee = BankAccount_7.doubleValidator(input, "Enter monthly fee : ");

        CheckingAccountWithInterest checkingAccountWithInterestObject = new CheckingAccountWithInterest(accountNumberGenerated,
                                                                        opbalance, bankBranchObject, noofChecks, monthlyFee);

        for (Client client : Clients) {
            if (client.getName().equalsIgnoreCase(username)) {
                for (int c = 0; c < client.getCheckingAccountWithInterestsArray().length; c++) {
                    if (client.getCheckingAccountWithInterestsArray()[c] == null) {
                         client.getCheckingAccountWithInterestsArray()[c] = checkingAccountWithInterestObject;
                        break;
                    }
                }
            }
        }

        System.out.println();
        System.out.println("User Details Saved Successfully & Bank Account created !!! \n");

        System.out.println("--------------------------------------------");
        System.out.println(checkingAccountWithInterestObject.getHomeBranch());
        System.out.println("Username                  : " + username);
        System.out.println("New Account Number        : " + checkingAccountWithInterestObject.getAccountNumber());
        System.out.println("Account Balance           : $" + checkingAccountWithInterestObject.getBalance());
        System.out.println("Interest Rate             : "+checkingAccountWithInterestObject.getInterest());

        System.out.println("--------------------------------------------\n");

        System.out.println("Do you want to create new Account ? | yes : Y | Menu : any key");

        String acv = input.next();

        if (acv.equalsIgnoreCase("y")) {
            if (noOfAcc < 5) {
                noOfAcc++;
                enterAccountData(username);

            } else {
                System.out.println("You can't create more than 5 Accounts");
                staffMenu();
            }


        } else {
            staffMenu();
        }


    }
//----------------------------------------------------------------------------------------------------------------------

    //  View Account Details
    private static void displayAccount(String id, int account) {
        if (clientExist(id)) {
            Client cli = clientFind(id);

            if (cli.accountExist(account)) {
                Account acc = cli.accountFind(account);
                System.out.println("-----------------------------------------------------------");
                System.out.println("          Account Details of " + cli.getName());
                System.out.println("-----------------------------------------------------------");
                System.out.println(acc.getHomeBranch());
                System.out.println("Account ID                : \t" + acc.getAccountNumber());
                System.out.println("Name                      : \t" + cli.getName());
                System.out.println("NIC number                : \t" + cli.getIdnum());
                System.out.println("Phone                     : \t" + cli.getPhone());
                System.out.println("User Password             : \t" + cli.getuPassword());
                System.out.println("Balance                   : \t" + acc.getBalance() + " USD ($)");
                System.out.println("------------------------------------------------------------");
            } else {
                System.err.println("No Account Found In Our System !!!");
            }
        } else {
            System.err.println("No Client Found In Our System !!!");
        }
    }

//----------------------------------------------------------------------------------------------------------------------

    //   call function view_account()
    private static void view() {
        Scanner input = new Scanner(System.in);
        System.out.println();

        for (Client client : Clients) {
            System.out.println("Client name :- " + client.getName());
        }

        System.out.println("Enter client name to display acounts");

        String clienttodisplay = input.next();
        for (Client clientElement : Clients) {
            try {
                if (clientElement.getName().equalsIgnoreCase(clienttodisplay)) {


                    for (int x = 0; x < clientElement.getCheckingAccountWithInterestsArray().length; x++) {

                        System.out.println("--------------------------------------------");
                       // System.out.println(clientElement.getCheckingAccountWithInterestsArray()[x].getHomeBranch());
                        clientElement.getCheckingAccountWithInterestsArray()[x].displayBranch();
                        System.out.println("Username                  : " + clientElement.getName());
                        System.out.println("New Account Number        : " + clientElement.getCheckingAccountWithInterestsArray()[x].getAccountNumber());
                        System.out.println("Account Balance           : $" + clientElement.getCheckingAccountWithInterestsArray()[x].getBalance());
                        System.out.println("Interest Rate             : "+clientElement.getCheckingAccountWithInterestsArray()[x].getInterest());

                        System.out.println("--------------------------------------------\n");

                    }
                }
            } catch (NullPointerException e) {
                //System.err.println("Sorry (:/) !!! \nData Not found (Null point exception) !!!");
            }
        }
        System.out.println();
        System.out.print("Return the main menu (Y)");
        String enter = input.next();
        if (enter.equals("Y")) {
            staffMenu();
        } else {
            staffMenu();
        }
    }
//----------------------------------------------------------------------------------------------------------------------
    //report generation method
    public static void produceReport(){
            Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------------");
        System.out.println("-----------------Start of the report--------------");
        System.out.println("--------------------------------------------------\n");

        for (Client client:Clients)
        {
            for (int c=0;c<client.getCheckingAccountWithInterestsArray().length;c++)
            {
               if (!(client.getCheckingAccountWithInterestsArray()[c]==null)){
                   //System.out.println("--------------------------------------------------");
                   System.out.println("==================================================");
                   System.out.println(client.getCheckingAccountWithInterestsArray()[c].getHomeBranch());
                   System.out.println("Username                  : "+client.getName());
                   System.out.println();
                  // System.out.printf("%25s %15s","Account Number   :- ",client.getCheckingAccountWithInterestsArray()[c].getAccountNumber());
                   System.out.println("Account Number            : "+client.getCheckingAccountWithInterestsArray()[c].getAccountNumber());
                   System.out.println();
                   System.out.println("Account Balance           : "+client.getCheckingAccountWithInterestsArray()[c].getBalance());
                   System.out.println();
                   System.out.println("Interest                  : "+client.getCheckingAccountWithInterestsArray()[c].getInterest());
                   System.out.println();
                   System.out.println("Monthly fee               : "+client.getCheckingAccountWithInterestsArray()[c].getMonthlyFee());
                   System.out.println();
                   System.out.println("Number of Checks          : "+client.getCheckingAccountWithInterestsArray()[c].getNoofChecks());
                   System.out.println();
                   System.out.println("==================================================\n\n");
                  // System.out.println(client.getCheckingAccountWithInterestsArray()[c].getHomeBranch());
               }
            }
        }
                   System.out.println("-----------------End Of Report--------------------");


        System.out.println();
        System.out.print("Return the main menu (Y)");
        String enter = input.next();
        if (enter.equals("Y")) {
            staffMenu();
        } else {
            staffMenu();
        }
    }
    //----------------------------------------------------------------------------------------------------------------------
    //  Client Login
    static Client clientLogin() {
        System.out.println("Enter Your id Number: ");
        Scanner input = new Scanner(System.in);
        String id = input.nextLine();
        Client current = new Client("", "", 0);
        if (clientExist(id)) {
            current = clientFind(id);
            System.out.println("Enter Your Password: ");
            String Password = input.next();
            if (!Password.equals(current.getuPassword())) {
                System.err.println("Password incorrect. Try Again");
                clientLogin();
            }
        } else {
            System.err.println("Account Number incorrect. Try Again");
            clientLogin();
        }
        return current;
    }

//----------------------------------------------------------------------------------------------------------------------

    //  view info to Client
    private static void viewinfo() {
        Client cli = clientLogin();
        Scanner input = new Scanner(System.in);
        boolean found = false;
        int account = 0;
        while (!found) {
            account = intValidator(input, "Enter Account Number: ");
            found = true;
        }
        displayAccount(cli.getIdnum(), account);
    }

//----------------------------------------------------------------------------------------------------------------------
    //  Main Method
    public static void main(String args[]) {
        loginType();
    }
}

