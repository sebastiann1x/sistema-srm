package Models;

import Services.UserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Transaction {
    private long id;
    private TransactionType type;
    private String description;
    private long originAccount;
    private long destinyAccount;
    private float amount;
    private LocalDate date;

    public Transaction() {
    }

    public Transaction(long id, TransactionType type, String description, long originAccount, long destinyAccount, float amount) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.originAccount = originAccount;
        this.destinyAccount = destinyAccount;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getOriginAccount() {
        return originAccount;
    }

    public void setOriginAccount(long originAccount) {
        this.originAccount = originAccount;
    }

    public long getDestinyAccount() {
        return destinyAccount;
    }

    public void setDestinyAccount(long destinyAccount) {
        this.destinyAccount = destinyAccount;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public String toString() {
        return id + "|" + type + "|" + description + "|" + originAccount + "|" + destinyAccount + "|" + amount + "|" + date;
    }

}
