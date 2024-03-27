package africa.semicolon.FinalDiary.dto;


import lombok.Data;

@Data
public class DeleteEntryRequest {
    private String id;
    private String author;
}
