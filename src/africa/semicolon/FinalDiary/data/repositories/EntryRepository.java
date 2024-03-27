package africa.semicolon.FinalDiary.data.repositories;
import africa.semicolon.FinalDiary.data.model.*;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EntryRepository extends MongoRepository<Entry, String> {
    List<Entry> findByAuthor(String username);
}
