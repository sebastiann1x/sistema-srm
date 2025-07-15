package Repositories;

import Exceptions.IdCounterException;
import Models.IdType;
import Util.IdCounterFile;

import java.io.*;
import java.util.Arrays;

public class IdCounterRepository {

    private final IdCounterFile idCounterFile = IdCounterFile.getInstance();
    private final File file = idCounterFile.getFile();

    public IdCounterRepository() {
    }

    public void saveCounter(IdType idType, Long idCounter) throws IOException {
        switch (idType) {
            case USERID -> updateIds(0, idCounter);
            case ACCOUNTID -> updateIds(1, idCounter);
            case TRANSACTIONID -> updateIds(2, idCounter);
        }
    }

    public long getLastId(IdType idType) throws IOException {
        switch (idType) {
            case USERID:
                return getIdByPosition(0);
            case ACCOUNTID:
                return getIdByPosition(1);
            case TRANSACTIONID:
                return getIdByPosition(2);
            default:
                throw new IdCounterException("Invalid IdType provided.");
        }
    }

    private long[] readIds() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line = reader.readLine();
            if (line != null) {
                String[] idCounters = line.split("\\|");
                return Arrays.stream(idCounters)
                        .mapToLong(Long::parseLong)
                        .toArray();
            }
            throw new IdCounterException("The idCounter File is empty.");
        }
    }

    private void persistIds(long[] idCounters) throws IOException {
        String updatedLine = String.join("|", Arrays.stream(idCounters)
                .mapToObj(String::valueOf)
                .toArray(String[]::new));

        try (FileWriter writer = new FileWriter(file, false)) {
            writer.write(updatedLine + "\n");
        }
    }

    private void updateIds(int position, long idCounter) throws IOException {
            long[] idCounters = readIds();
            if (position < 0 || position >= idCounters.length) {
                throw new IdCounterException("Invalid position for counter update.");
            }

            idCounters[position] = idCounter;
            persistIds(idCounters);
    }

    private long getIdByPosition(int position) throws IOException {
        long[] idCounters = readIds();
        if (position < 0 || position >= idCounters.length) {
            throw new IdCounterException("Invalid position for ID retrieval");
        }
        return idCounters[position];
    }
}
