package Services;

import Models.Account;
import Models.Operation;
import Repositories.UserRepository;
import Models.User;

public class UserService {

    private final UserRepository userRepository;
    private final IdCounterService idCounterService;

    public UserService(UserRepository userRepository, IdCounterService idCounterService) {
        this.userRepository = userRepository;
        this.idCounterService = idCounterService;
    }
    
    
  
    public boolean login(String userName, String pin) {
        User user = userRepository.getUserByUser(userName);
        System.out.println(userName);
        System.out.println(pin);
        return user != null && user.getPin().equals(pin);
    }
    

   
    public boolean createUser(String userId, String name, String idNumber, String pin) {
        User user = new User();
        user.setId(userId);
        user.setName(name);
        user.setNationalId(idNumber);
        user.setPin(pin);
        return userRepository.createUserInFile(user);
    }

}
