package Repositories;

import Exceptions.TransactionException;
import Models.Account;
import Models.Operation;

import Util.AccountsFile;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class AccountRepository {

    private AccountsFile accountsFile = AccountsFile.getInstance();

    private File file = accountsFile.getFile();


    public AccountRepository() {

    }

    public void saveAccount(Account account) throws IOException {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(account.toString() + "\n");
        }
    }


    public List<Account> getAccountsByClientId(long clientId) throws IOException {
        List<Account> clientAccounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(foundAccount(line, clientId, true)){
                    clientAccounts.add(createAccount(line));
                }
            }
        }
        return clientAccounts;
    }

    public Optional<Account> getAccountById(long accountId) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(foundAccount(line, accountId, false)){
                    return Optional.of(createAccount(line));
                }
            }
        }
        return Optional.empty();
    }


    private boolean foundAccount(String line, long id, boolean isClient){
        String[] attributes = line.split("\\|");
        if(!isClient){
            return Long.parseLong(attributes[0]) == id;
        }
        return Long.parseLong(attributes[1]) == id;
    }


    private Account createAccount(String line) {
        String[] attributes = line.split("\\|");
        Account a = new Account();
        a.setId(Long.parseLong(attributes[0]));
        a.setClientId(Long.parseLong(attributes[1]));
        a.setName(attributes[2]);
        a.setBalance(Float.parseFloat(attributes[3]));

        return a;
    }

    private List<Account> listAllAccounts() throws IOException {
        List<Account> accounts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                accounts.add(createAccount(line));
            }
        }

        return accounts;
    }

    private void updateAccount(Account updatedAccount) throws IOException {
        List<Account> accounts = listAllAccounts();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Account account : accounts) {
                if (account.getId() == updatedAccount.getId()) {
                    writer.write(updatedAccount + "\n");
                } else {
                    writer.write(account.toString() + "\n");
                }
            }
        }
    }

    public void modifyBalance(Long accountId, float amount, Operation operation) throws IOException {
        Optional<Account> optionalAccount = getAccountById(accountId);

        if (optionalAccount.isEmpty()) {
            throw new TransactionException("The operation wasn't processed because the account couldn't be accessed.");
        }

        Account account = optionalAccount.get();

        switch (operation) {
            case ADD -> account.setBalance(account.getBalance() + amount);
            case SUBSTRACT -> {
                if(account.getBalance() < amount){
                    JOptionPane.showMessageDialog(null, "The transaction failed due to Insuficcient funds");
                    throw new TransactionException("The transaction failed due to Insuficcient funds");
                }
                account.setBalance(account.getBalance() - amount);
            }
        }

        updateAccount(account);
    }

}