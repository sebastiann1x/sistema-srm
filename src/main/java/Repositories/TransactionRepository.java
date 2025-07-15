package Repositories;

import Models.Account;
import Models.Transaction;
import Models.TransactionType;
import Util.TransactionsFile;
import Util.UsersFile;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository {
    private TransactionsFile transactionsFile = TransactionsFile.getInstance();

    private File file = transactionsFile.getFile();

    public TransactionRepository() {
    }

    public void saveTransaction(Transaction transaction) throws IOException {
        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(transaction.toString() + "\n");
        }
    }


    public Optional<Transaction> getTransactionById(long id) throws IOException {

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if(foundTransaction(line, id)){
                    return Optional.of(createTransaction(line));
                }
            }
        }
        return Optional.empty();
    }



    private boolean foundTransaction(String line, long id){
        String[] attributes = line.split("\\|");

        return Long.parseLong(attributes[0]) == id;

    }

    private Transaction createTransaction(String line){
        String[] attributes = line.split("\\|");
        Transaction t = new Transaction();
        t.setId(Long.parseLong(attributes[0]));
        t.setType(TransactionType.valueOf(attributes[1]));
        t.setDescription(attributes[2]);
        t.setOriginAccount(Long.parseLong(attributes[3]));
        t.setDestinyAccount(Long.parseLong(attributes[4]));
        t.setAmount(Float.parseFloat(attributes[5]));
        t.setDate(LocalDate.parse(attributes[6]));

        return t;

    }

}
