package Services;

import Exceptions.TransactionException;
import Models.IdType;
import Models.Transaction;
import Models.TransactionType;
import Repositories.TransactionRepository;

import java.io.IOException;
import java.util.Optional;
import javax.swing.JOptionPane;

public class TransactionService {
    private final TransactionRepository repo;
    private final AccountService accountService;
    private final IdCounterService idCounterService;

    public TransactionService(TransactionRepository repo, AccountService accountService, IdCounterService idCounterService) {
        this.repo = repo;
        this.accountService = accountService;
        this.idCounterService = idCounterService;
    }

    public Transaction addTransaction(TransactionType type, String description, Long origin, Long destiny, float amount){
        long newTransactionId;
        try {
            newTransactionId = (idCounterService.getLastId(IdType.TRANSACTIONID) + 1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Transaction t = new Transaction(newTransactionId, type, description, origin, destiny, amount);

        try {
            accountService.startTransaction(t);
            saveTransaction(t);
            idCounterService.saveCounter(IdType.TRANSACTIONID, newTransactionId);
            return t;

        } catch (IOException e) {

            throw new TransactionException("The operation has failed. Changes weren't saved in the data base.");
        }



    }


    public Transaction getTransactionById(long transactonId){
        Optional<Transaction> optionalTransaction = null;
        try {
            optionalTransaction = repo.getTransactionById(transactonId);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (optionalTransaction.isEmpty()) {
            JOptionPane.showMessageDialog(null, "The transaction couldn't be found");
            throw new TransactionException("The transaction couldn't be found");
        }

        return optionalTransaction.get();
    }


    private  void saveTransaction(Transaction transaction){
        try {
            repo.saveTransaction(transaction);

        } catch (IOException e) {

            throw new TransactionException("The transaction was processed, but the changes couldn't be saved");
        }
    }
}
