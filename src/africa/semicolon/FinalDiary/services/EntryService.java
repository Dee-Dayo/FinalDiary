package africa.semicolon.FinalDiary.services;

import africa.semicolon.FinalDiary.data.model.Entry;

import java.util.List;

public interface EntryService {
    void save(Entry entry);

    List<Entry> findEntriesByUsername(String username);

    void deleteEntrybyId(String id);
}
