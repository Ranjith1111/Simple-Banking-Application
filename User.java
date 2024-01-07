import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

class User{
    private String firstName;
    private String lastName;
    private ArrayList<Account> accounts;
    private String userID;
    private byte[] pin;

    public User(String firstName, String lastName, String pin, Bank bank){
        this.firstName = firstName;
        this.lastName = lastName;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pin = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("NoSuchAlgorithmException error");
            e.printStackTrace();
            System.exit(0);
        }

        this.userID = bank.getNewUserID();

        this.accounts = new ArrayList<Account>();

        System.out.printf("*****ACCOUNT SUCCESSFULLY CREATED*****\nUser Name : %s\nUser ID : %s\n",this.firstName,this.userID);

    }

    public void addAccount(Account account){
        this.accounts.add(account);
    }

    public String getUserID(){
        return this.userID;
    }

    public boolean validatePin(String pin){
        MessageDigest md;
        byte[] userPin;
        try {
            md = MessageDigest.getInstance("MD5");
            userPin = md.digest(pin.getBytes());
            return MessageDigest.isEqual(userPin, this.pin);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Found NoSuchAlgorithmException error");
            e.printStackTrace();
            System.exit(0);
        }
        
        return false;
    }

    public String getUserName(){
        return this.firstName;
    }

    public void printAccountsSummary(){
        System.out.printf("%s's Accounts Summary\n",this.firstName);
        for(int i = 0 ; i < this.accounts.size() ; i++){
            System.out.printf("%d. %s\n",i+1,this.accounts.get(i).getSummary());
        }
        System.out.println();
    }

    public int getAccountsLen(){
        return this.accounts.size();
    }

    public Account getAccount(int acc){
        return this.accounts.get(acc);
    }

    public void transferMoney(Account acc , double amount , String memo){
        acc.transferMoney(amount, memo);
    }

}