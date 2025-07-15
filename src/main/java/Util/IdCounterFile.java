package Util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IdCounterFile {
    private static IdCounterFile instance;
    private final File file;

    private IdCounterFile() {
        this.file = new File("src/main/java/Database/idcounters.txt");

        try {
            if (file.createNewFile()) {
                try (FileWriter writer = new FileWriter(file)) {
                    writer.write("0|0|0");
                    System.out.println("File created and initialized in: " + file.getAbsolutePath());
                } catch (IOException e) {
                    System.out.println("An error occurred while attempting to initialize the file");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists in: " + file.getAbsolutePath());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while attempting to create the file");
            e.printStackTrace();
        }
    }

    public static IdCounterFile getInstance() {
        if (instance == null) {
            instance = new IdCounterFile();
        }
        return instance;
    }

    public File getFile() {
        return file;
    }
}
