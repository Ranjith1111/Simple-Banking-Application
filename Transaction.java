import java.util.Date; 

class Transaction{
    private double amount;
    private Date timestamp;
    private String memo;
    private Account inAccount;

    public Transaction(double amount , Account inAccount){
        this.amount = amount;
        this.inAccount = inAccount;
        this.timestamp = new Date();
    }

    public Transaction(double amount , String memo , Account inAccount){
        this(amount , inAccount);
        this.memo = memo;
    }

    public double getAmount(){
        return this.amount;
    }

    public String getDate(){
        return this.timestamp.toString();
    }

    public String getMemo(){
        return this.memo;
    }

}