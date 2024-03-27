package africa.semicolon.FinalDiary.services;
import africa.semicolon.FinalDiary.data.model.Diary;
import africa.semicolon.FinalDiary.data.model.Entry;
import africa.semicolon.FinalDiary.dto.*;
import africa.semicolon.FinalDiary.data.repositories.*;
import africa.semicolon.FinalDiary.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryServiceImpl implements DiaryService{

    @Autowired
    private DiaryRepository diaryRepository;
    @Autowired
    private EntryService entryService;

    @Override
    public void register(RegisterRequest registerRequest) {
        validateUsername(registerRequest);

        Diary diary = new Diary();
        diary.setUsername(registerRequest.getUsername());
        diary.setPassword(registerRequest.getPassword());
        diaryRepository.save(diary);
    }

    private void validateUsername(RegisterRequest registerRequest) {
        Diary diary = diaryRepository.findByUsername(registerRequest.getUsername());
        if (diary != null) throw new UsernameAlreadyExist(registerRequest.getUsername() + " username already exist");
    }

    @Override
    public long getNoOfUsers() {
        return diaryRepository.count();
    }

    @Override
    public void login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        Diary diary = diaryRepository.findByUsername(username);
        validateDiary(diary, "User not found");
        validatePassword(diary, password);
        diary.setLocked(false);
        diaryRepository.save(diary);
    }

    private static void validateDiary(Diary diary, String User_not_found) {
        if (diary == null) throw new DiaryNotFoundException(User_not_found);
    }

    private static void validatePassword(Diary diary, String password) {
        if(diary.getPassword().equals(password)) diary.setLocked(false);
        else throw new InvalidPassword("Password incorrect");
    }
    @Override
    public Diary findDiaryByUsername(String username) {
         Diary diary = diaryRepository.findByUsername(username);
        validateDiary(diary, "Diary not found");
        return diary;
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {
        String username = logoutRequest.getUsername();
        Diary diary = diaryRepository.findByUsername(username);
        if (diary == null) throw new DiaryNotFoundException("User not found");
        diary.setLocked(true);
        diaryRepository.save(diary);
    }

    @Override
    public void deleteUser(DeleteUserRequest deleteDiaryRequest) {
        String username = deleteDiaryRequest.getUsername();
        String password = deleteDiaryRequest.getPassword();
        Diary diary = diaryRepository.findByUsername(username);
        validatePassword(diary, password);
        diaryRepository.delete(diary);
    }

    @Override
    public void createEntry(CreateEntryRequest createEntryRequest) {
        String author = createEntryRequest.getAuthor();
        Diary diary = findDiaryByUsername(author);
        isLockedStatus(diary);

        validateEntry(createEntryRequest);

        Entry entry = new Entry();
        entry.setAuthor(author);
        entry.setTitle(createEntryRequest.getTitle());
        entry.setBody(createEntryRequest.getBody());
        entryService.save(entry);
    }

    private void validateEntry(CreateEntryRequest createEntryRequest) {
        List<Entry> userEntries = entryService.findEntriesByUsername(createEntryRequest.getAuthor());
        for(Entry entry : userEntries){
            if (entry.getTitle().equals(createEntryRequest.getTitle())) {
                throw new EntryTitleAlreadyCreated("Entry title already created");
            }
        }
    }

    private void isLockedStatus(Diary diary) {
        if(diary.isLocked()) throw new DiaryLockedException("You need to login to create Entry");
    }

    @Override
    public void deleteEntry(DeleteEntryRequest deleteEntryRequest) {
        Diary diary = findDiaryByUsername(deleteEntryRequest.getAuthor());
       isLockedStatus(diary);

       entryService.deleteEntrybyId(deleteEntryRequest.getId());
    }
}
