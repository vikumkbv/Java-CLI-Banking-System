import java.io.Serializable;

//Class for create a new Client
public class Client implements Serializable {

    private String name;
    private String idnum;
    private int phone;
    private String uPassword;
    private static CheckingAccountWithInterest [] checkingAccountWithInterestsArray=new CheckingAccountWithInterest[5];

    Client(String nameD, String idnum, int phoneD) {
        this.name = nameD;
        this.idnum = idnum;
        this.phone = phoneD;
        this.uPassword = "N/A";
    }

    public Client(String name, String idnum, int phone, String uPassword) {
        this.name = name;
        this.idnum = idnum;
        this.phone = phone;
        this.uPassword = uPassword;
        this.checkingAccountWithInterestsArray=new CheckingAccountWithInterest[5];
    }


    //getters
    public String getName() {
        return this.name;
    }

    public static CheckingAccountWithInterest[] getCheckingAccountWithInterestsArray() {
        return checkingAccountWithInterestsArray;
    }


 public boolean accountExist(int accountNumber)
 {
     boolean accountExist=false;
     if (!(validAcc(checkingAccountWithInterestsArray)==0))
     {
        for (int z=0;z<validAcc(checkingAccountWithInterestsArray);z++)
        {
            if (checkingAccountWithInterestsArray[z].getAccountNumber()==accountNumber)
            {
                accountExist=true;
            }
        }
     }
     return accountExist;
 }

    //Search required account_id from the users array and return the Account;

    public static CheckingAccountWithInterest accountFind(int account)
    {
        CheckingAccountWithInterest object=new CheckingAccountWithInterest();
        for (int z=0;z<validAcc(checkingAccountWithInterestsArray);z++)
        {
            if (checkingAccountWithInterestsArray[z].getAccountNumber()==account)
            {
                object=checkingAccountWithInterestsArray[z];
            }
        }
        return object;
    }

    public String getIdnum() {
        return this.idnum;
    }

    public int getPhone() {
        return this.phone;
    }

    public String getuPassword() {
        return this.uPassword;
    }

    public static int validAcc(CheckingAccount [] arr)
    {
        int noAccounts=0;
        for (int z=0;z<arr.length;z++)
        {
            if (!(arr[z]==null))
            {
                noAccounts++;
            }
        }
        return noAccounts;
    }

    public void setAccount(CheckingAccountWithInterest object) {
       for (int i=0;i<checkingAccountWithInterestsArray.length;i++)
       {
           if (validAcc(checkingAccountWithInterestsArray)==5)
           {
               System.err.println("You have reached the account limit of 5 checking accounts");
               break;
           }
           if (!(checkingAccountWithInterestsArray[i]==null))
           {
               checkingAccountWithInterestsArray[i]=object;
               break;
           }
       }
    }



}
