package Util;

import java.io.File;
import java.io.IOException;

public class TransactionsFile {
    private static TransactionsFile instance;
    private final File file;

    private TransactionsFile() {
        this.file = new File("src/main/java/Database/transactions.txt");

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

    public static TransactionsFile getInstance() {
        if (instance == null) {
            instance = new TransactionsFile();
        }
        return instance;
    }

    public File getFile() {
        return file;
    }
}
