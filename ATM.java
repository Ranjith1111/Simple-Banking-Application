import java.util.Scanner;

class ATM{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);

        Bank bank = new Bank("Indian");

        User user1 = bank.addUser("Ranjith", "Sam", "12345");

        Account account = new Account("Savings" , user1,bank);
        

        User curUser;
        while(true){
            curUser = ATM.mainManuPrompt(bank);

            ATM.printUserMenu(curUser,bank);
        }

    }

    public static User mainManuPrompt(Bank bank){
        Scanner scan = new Scanner(System.in);
        User tempUser;
        do{
            System.out.printf("\nWelcome to %s\n",bank.getBankName());
            System.out.print("Enter the User ID : ");
            String userId = scan.nextLine();
            System.out.print("Enter the PIN : ");
            String pin = scan.nextLine();

            tempUser = bank.userLogin(userId, pin);
            if(tempUser == null){
                System.out.println("Entered User ID or PIN is incorrect.......................\nPlease try again :)");
            }
        }while(tempUser == null);

        return tempUser;
    }

    public static void printUserMenu(User user , Bank bank){
        Scanner scan = new Scanner(System.in);
        user.printAccountsSummary();

        int choice;
        do{
            System.out.printf("Welcome %s, What do you want to do?\n",user.getUserName());
            System.out.println("1. Create New Account\n2. Show account transaction history\n3. Withdraw\n4. Deposite\n5. Transfer\n6. Exit");
            System.out.print("Enter the choice : ");
            choice = scan.nextInt();

            if(choice > 6 || choice < 1){
                System.out.println("Invalid Choice..............\nPLEASE ENTER 1 to 5\n");
            }

        }while(choice > 6 || choice < 1);


        switch(choice){
            case 1:
                ATM.createAccount(user, bank);
                break;
            case 2:
                ATM.showAccountTransactionHistory(user);
                break;
            case 3 :
                ATM.withdraw(user);
                break;
            case 4: 
                ATM.deposite(user);
                break;
            case 5:
                ATM.transfer(user);
                break;
            case 6:
                System.out.println("Thank You for your Survice :)");
                break;
        } 
        if(choice != 6){
            ATM.printUserMenu(user, bank);
        }      
        
    }

    public static void showAccountTransactionHistory(User user){
        Scanner scan = new Scanner(System.in);
        int acc ;
        // user.printAccountsSummary();

        do{
            System.out.printf("Which accuont do you wants to operate? \nEnter the number from 1 to %d : ",user.getAccountsLen());
            acc = scan.nextInt() - 1;   
            if(acc < 0 || acc >= user.getAccountsLen()){
                System.out.printf("Invalid Choice !!!!!!\nPLEASE ENTER A VALID NUMBER (1 - %d)\n",user.getAccountsLen());
            }
        }while(acc < 0 || acc >= user.getAccountsLen());
        

        Account neededAcc = user.getAccount(acc);
        neededAcc.printTransaction();
    }

    public static void transfer(User user){
        Scanner scan = new Scanner(System.in);
        int fAcc;
        int tAcc;
        double fromAccBalance;
        Account fromAcc;
        Account toAcc;
        double transferAmount;

        do{
            System.out.printf("Which account do you want to transfer the money from? (1 - %d) \nEnter your choice : ",user.getAccountsLen());
            fAcc = scan.nextInt()-1;

            if(fAcc < 0 || fAcc >= user.getAccountsLen()){
                System.out.println("Invalid Choice!!!!!\tTRY AGAIN........\n");
            }
        }while(fAcc < 0 || fAcc >= user.getAccountsLen());
        fromAcc = user.getAccount(fAcc);
        fromAccBalance = fromAcc.getBalance();

        do{
            System.out.printf("Which account do you want to transfer the money to? (1 - %d)\nEnter your choice : ",user.getAccountsLen());
            tAcc = scan.nextInt() - 1;

            if(tAcc < 0 || tAcc >= user.getAccountsLen()){
                System.out.println("Invalid Choice!!!!!\nTRY AGAIN........\n");
            }
        }while(tAcc < 0 || tAcc >= user.getAccountsLen());
        toAcc = user.getAccount(tAcc);

        do{
            System.out.print("Enter the amount to transfer : ");
            transferAmount = scan.nextDouble();
            if(transferAmount < 0 || transferAmount > fromAccBalance){
                System.out.println("Invalid Amount!!!!!\nTRY AGAIN........\n");
            }
        }while(transferAmount < 0 || transferAmount > fromAccBalance);

        System.out.println();
        // System.out.print("Please Enter the Memo : ");
        // String memo = scan.nextLine();

        // user.transferMoney(toAcc,transferAmount,String.format("Received Money from %s",fromAcc.getAccountID()));
        // user.transferMoney(fromAcc,-1*transferAmount,String.format("Send Money to %s",toAcc.getAccountID()));
        user.transferMoney(fromAcc , -1*transferAmount,"Send Money to "+toAcc.getAccountID());
        user.transferMoney(toAcc , transferAmount,"Received Money from "+fromAcc.getAccountID());
    }

    public static void withdraw(User user){
        Scanner scan = new Scanner(System.in);
        double transferAmount = 0;
        int fAcc;
        Account fromAcc;
        double accBalance;
        String memo;

        do{
            System.out.printf("Which account do you wants to withdraw money from? (1 - %d)\nEnter your choice : ",user.getAccountsLen());
            fAcc = scan.nextInt()-1;

            if(fAcc < 0 || fAcc >= user.getAccountsLen()){
                System.out.println("Invalid Choice!!!!!\nTRY AGAIN.......\n");
            }
        }while(fAcc < 0 || fAcc >= user.getAccountsLen());
        fromAcc = user.getAccount(fAcc);
        accBalance = fromAcc.getBalance();

        do{
            System.out.print("Enter the Amount to Withdraw : ");
            transferAmount = scan.nextDouble();

            if(transferAmount < 0 || transferAmount > accBalance){
                System.out.println("Invalid Amount!!!!!\nTRY AGAIN......\n");
            }
        }while(transferAmount < 0 || transferAmount > accBalance);
        scan.nextLine();

        System.out.print("Enter a memo : ");
        memo = scan.nextLine();

        user.transferMoney(fromAcc, -1*transferAmount, memo);
    }

    public static void deposite(User user){
        Scanner scan = new Scanner(System.in);
        int fAcc;
        Account acc;
        double amount;
        String memo;

        do{
            System.out.printf("Which account do you wants to deposite money?(1 - %d)\nEnter your choice : ",user.getAccountsLen());
            fAcc = scan.nextInt()-1;

            if(fAcc < 0 || fAcc >= user.getAccountsLen()){
                System.out.println("Invalid choice!!!!!\nTRY AGAIN.......\n");
            }
        }while(fAcc < 0 || fAcc >= user.getAccountsLen());
        acc = user.getAccount(fAcc);

        do{
            System.out.print("Enter the amount to deposite : ");
            amount = scan.nextDouble();

            if (amount < 0 ){
                System.out.println("Invalid amount!!!!!\nTRY AGAIN.......\n");    
            }
        }while(amount < 0);

        scan.nextLine();

        System.out.print("Enter a memo : ");
        memo = scan.nextLine();

        user.transferMoney(acc, amount, memo);
    }

    public static void createAccount(User user , Bank bank ){
        Scanner scan = new Scanner(System.in);
        String accName;
        System.out.print("Enter the account name : ");
        accName = scan.nextLine();

        new Account(accName, user, bank);
    }

}