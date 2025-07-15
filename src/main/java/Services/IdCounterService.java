package Services;

import Models.IdType;
import Repositories.IdCounterRepository;

import java.io.IOException;

public class IdCounterService {
    private final IdCounterRepository repo;

    public IdCounterService(IdCounterRepository idCounterRepository) {
        this.repo = idCounterRepository;
    }

    public void saveCounter(IdType idType, Long idCounter) throws IOException {
        repo.saveCounter(idType, idCounter);
    }

    public long getLastId(IdType idType) throws IOException {
        return repo.getLastId(idType);
    }

}
