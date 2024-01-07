import java.util.ArrayList;

class Account{
    private String AccountID;
    private ArrayList<Transaction> transactions;
    private User holder;
    private String name;

    public Account(String name , User holder, Bank bank){
        this.name = name;
        this.holder = holder;
        this.transactions = new ArrayList<Transaction>();

        this.AccountID = bank.getNewAccountID();

        holder.addAccount(this);
        bank.addAccount(this);
    }

    public String getAccountID(){
        return this.AccountID;
    }

    public String getSummary(){
        double balance = this.getBalance();

        return String.format("%s : ₹%.02f : %s",this.AccountID,balance,this.name);
    }

    public double getBalance(){
        double balance = 0;
        for(Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
    }

    public void printTransaction(){
        System.out.println();
        for(int i = 0 ; i < this.transactions.size() ; i++){
            System.out.printf("%d : %s : ₹%.02f : %s\n",i+1,this.transactions.get(i).getDate(), this.transactions.get(i).getAmount() , this.transactions.get(i).getMemo() );
        }
        System.out.println();
    }

    public void transferMoney(double am , String memo){
        Transaction transfer = new Transaction(am, memo, this);
        this.transactions.add(transfer);
    }

}