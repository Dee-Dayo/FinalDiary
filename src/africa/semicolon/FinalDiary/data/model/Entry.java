package africa.semicolon.FinalDiary.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class Entry {

    @Id
    private int id;
    private String title;
    private String author;
    private String body;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
