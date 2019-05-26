import java.io.Serializable;

public  class BankBranch implements Serializable {

    private static int BSBnumber;
    private static String address;
    private static int postcode;

    public BankBranch(int BSBnumber, String address, int postcode) {
        BankBranch.BSBnumber = BSBnumber;
        BankBranch.address = address;
        BankBranch.postcode = postcode;

    }

    @Override
    public String toString() {

        return  "BSB Number                : "+this.BSBnumber+
                "\nBank Address              : "+this.address+
                "\nPostal Code               : " +this.postcode;
    }

    public static int getBSBnumber() {
        return BSBnumber;
    }

    public static String getAddress() {
        return address;
    }

    public static int getPostcode() {
        return postcode;
    }



}
