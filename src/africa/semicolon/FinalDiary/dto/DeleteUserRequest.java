package africa.semicolon.FinalDiary.dto;

import lombok.Data;

@Data
public class DeleteUserRequest {
    private String username;
    private String password;
}
