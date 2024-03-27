package africa.semicolon.FinalDiary.services;
import africa.semicolon.FinalDiary.data.repositories.*;
import africa.semicolon.FinalDiary.data.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService{

    @Autowired
    private EntryRepository entryRepository;

    @Override
    public void save(Entry entry) {
        entryRepository.save(entry);
    }

    @Override
    public List<Entry> findEntriesByUsername(String username) {
        return entryRepository.findByAuthor(username);
    }

    @Override
    public void deleteEntrybyId(String id) {
        entryRepository.deleteById(id);
    }
}
