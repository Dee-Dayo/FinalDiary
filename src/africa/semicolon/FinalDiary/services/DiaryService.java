package africa.semicolon.FinalDiary.services;
import africa.semicolon.FinalDiary.data.model.Diary;
import africa.semicolon.FinalDiary.dto.*;


public interface DiaryService {
    void register(RegisterRequest registerRequest);
    long getNoOfUsers();
    void login(LoginRequest loginRequest);
    Diary findDiaryByUsername(String username);
    void logout(LogoutRequest logoutRequest);
    void deleteUser(DeleteUserRequest deleteDiaryRequest);
    void createEntry(CreateEntryRequest createEntryRequest);

    void deleteEntry(DeleteEntryRequest deleteEntryRequest);
}
