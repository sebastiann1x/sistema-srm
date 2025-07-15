package Models;

import Exceptions.TransactionException;
import Services.UserService;

import java.util.List;
import java.util.Objects;

public class Account {
    private long id;
    private long clientId;
    private String name;
    private float balance;

    public Account(long id, String name, long clientId) {
        this.id = id;
        this.name = name;
        this.clientId = clientId;
        this.balance = 0.0f;
    }

    public Account() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public String toString() {
        return id + "|" + clientId + "|" + name + "|" + balance;
    }



}
