import Models.Account;
import Models.Transaction;
import Models.TransactionType;
import Repositories.AccountRepository;
import Repositories.IdCounterRepository;
import Repositories.TransactionRepository;
import Repositories.UserRepository;
import Services.AccountService;
import Services.IdCounterService;
import Services.TransactionService;
import Services.UserService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IdCounterRepository idRepo = new IdCounterRepository();
        IdCounterService idService = new IdCounterService(idRepo);
        AccountRepository repo = new AccountRepository();
        AccountService aService = new AccountService(repo, idService);
        TransactionRepository trepo = new TransactionRepository();
        TransactionService tservice = new TransactionService(trepo, aService, idService);
        UserRepository uRepo = new UserRepository();
        UserService userService = new UserService(uRepo, idService);

        Transaction t1 = tservice.addTransaction(TransactionType.WITHDRAW, "Lotery", 2L, 2L, 5000.1f);

        System.out.println(t1);

    }
}
