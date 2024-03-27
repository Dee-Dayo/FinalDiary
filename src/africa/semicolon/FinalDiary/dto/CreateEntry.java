package africa.semicolon.FinalDiary.dto;

import lombok.Data;

@Data
public class CreateEntry {
    private String title;
    private String author;
    private String body;
}
