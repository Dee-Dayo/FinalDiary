package africa.semicolon.FinalDiary.dto;

import lombok.Data;

@Data
public class CreateEntryRequest {
    private String title;
    private String author;
    private String body;
}
