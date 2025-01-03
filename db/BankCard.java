package db;

public class BankCard {
    private final int numberLastDigits;
    private final int pinCode;
    private final int holderId;
    private double balance;
    private final String numberHash;

    public BankCard() {
        this.numberLastDigits = 0;
        this.pinCode = 0;
        this.holderId = 0;
        this.balance = 0.00;
        this.numberHash = "";
    }

    public BankCard(int numberLastDigits, int pinCode, int holderId, float balance, String numberHash) {
        this.numberLastDigits = numberLastDigits;
        this.pinCode = pinCode;
        this.holderId = holderId;
        this.balance = balance;
        this.numberHash = numberHash;
    }

    public int getNumberLastDigits() {
        return numberLastDigits;
    }

    public int getPinCode() {
        return pinCode;
    }

    public int getHolderId() {
        return holderId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumberHash() { return numberHash; }
}
