package Models;

public enum TransactionType {
    DEPOSIT("Deposit"),
    WITHDRAW("Withdrawal"),
    TRANSFER("Transfer");

    private String name;

    TransactionType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
