package Util;

import java.io.File;
import java.io.IOException;

public class AccountsFile {
    private static AccountsFile instance;
    private final File file;

    private AccountsFile() {
        this.file = new File("src/main/java/Database/accounts.txt");

        try {
            if (file.createNewFile()) {
                System.out.println("File created in: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists in: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error ocurred while attempting to create the file");
            e.printStackTrace();
        }
    }

    public static AccountsFile getInstance() {
        if (instance == null) {
            instance = new AccountsFile();
        }
        return instance;
    }

    public File getFile() {
        return file;
    }
}
