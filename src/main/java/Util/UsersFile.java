package Util;

import java.io.File;
import java.io.IOException;

public class UsersFile {
    private static UsersFile instance;
    private final File file;

    private UsersFile() {
        this.file = new File("src/main/java/Database/users.txt");

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

    public static UsersFile getInstance() {
        if (instance == null) {
            instance = new UsersFile();
        }
        return instance;
    }

    public File getFile() {
        return file;
    }
}
