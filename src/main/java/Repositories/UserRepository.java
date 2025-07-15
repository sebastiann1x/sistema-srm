package Repositories;

import Util.UsersFile;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import Models.User;

public class UserRepository {

    private UsersFile usersFile = UsersFile.getInstance();
    private File file = usersFile.getFile();

    public UserRepository() {
    }
    
    

    public User getUserById(String idNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (foundUser(line, idNumber)) {
                    return createUser(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public User getUserByUser(String userName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (foundUserByUser(line, userName)) {
                    return createUser(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean foundUserByUser(String line, String userName) {
        String[] userData = line.split("\\|");
        return userData[1].trim().equals(userName);
    }
   
    private boolean foundUser(String line, String idNumber) {
        String[] userData = line.split("\\|");
        return userData[2].trim().equals(idNumber);
    }


    private User createUser(String line) {
        String[] userData = line.split("\\|");
        User user = new User();
        user.setId(userData[0].trim());
        user.setName(userData[1].trim());
        user.setNationalId(userData[2].trim());
        user.setPin(userData[3].trim());
        return user;
    }

 
    public boolean createUserInFile(User user) {
        try (FileWriter writer = new FileWriter(file, true)) {
            String newUser = user.getId() + " | " +
                             user.getName() + " | " +
                             user.getNationalId() + " | " +
                             user.getPin() + "\n";
            writer.write(newUser);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    }


