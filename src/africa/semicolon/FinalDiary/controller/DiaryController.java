package africa.semicolon.FinalDiary.controller;

import africa.semicolon.FinalDiary.data.model.Entry;
import africa.semicolon.FinalDiary.dto.*;
import africa.semicolon.FinalDiary.exceptions.DiaryAppException;
import africa.semicolon.FinalDiary.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DiaryController {
    @Autowired
    private DiaryService diaryService;
    @Autowired
    private EntryService entryService;

    @PostMapping("/signup")
    public String register(@RequestBody RegisterRequest registerRequest) {
        try {
            diaryService.register(registerRequest);
            return "Registration Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String login(LoginRequest loginRequest) {
        try {
            diaryService.login(loginRequest);
            return "Login Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String logout(LogoutRequest logoutRequest) {
        try {
            diaryService.logout(logoutRequest);
            return "Logout Successful";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String addEntry(CreateEntryRequest createEntryRequest) {
        try {
            diaryService.createEntry(createEntryRequest);
            return "Entry added successfully";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public String deleteEntry(DeleteEntryRequest deleteEntryRequest) {
        try {
            diaryService.deleteEntry(deleteEntryRequest);
            return "Entry deleted successfully";
        } catch (DiaryAppException e){
            return e.getMessage();
        }
    }

    public List<Entry> getAllEntries(String username) {
        try {
            return entryService.findEntriesByUsername(username);
        } catch (DiaryAppException e){
            return List.of();
        }
    }
}
