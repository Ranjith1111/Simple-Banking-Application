import java.util.ArrayList;
import java.util.Random;

class Bank{
    private String name;
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    public Bank(String name){
        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    public String getNewUserID(){
        Random rand = new Random();
        String newID = "";
        int len = 8;
        boolean unique ;

        do{
            for(int i = 0 ; i < len ; i++){
                newID += ((Integer)rand.nextInt(10)).toString();
            }

            unique = false;
            for(User u : this.users){
                if(newID == u.getUserID()){
                    unique = true;
                    newID = "";
                    break;
                }
            }
        }while(unique);

        return newID;
    }

    public String getNewAccountID(){
        Random rand = new Random();
        String newID = "";
        boolean unique;
        int len = 10;

        do{
            for(int i = 0 ; i < len ; i++){
                newID += ((Integer)rand.nextInt(10)).toString();
            }
            unique = false;
            for(Account a : this.accounts){
                if(newID == a.getAccountID()){
                    unique = true;
                    newID = "";
                    break;
                }
            }
        }while(unique);

        return newID;
    }

    public void addAccount(Account account){
        this.accounts.add(account); 
    }

    public User addUser(String firstName , String lastName, String pin){
        User newUser = new User(firstName, lastName,pin,this);
        this.users.add(newUser);

        new Account("Savings",newUser,this);

        return newUser;
    }

    public User userLogin(String userId,String pin){
        for(User u : this.users ){
            if((u.getUserID().compareTo(userId)) == 0 && u.validatePin(pin)){
                return u;
            }
        }

        return null;
    }

    public String getBankName(){
        return this.name;
    }
}