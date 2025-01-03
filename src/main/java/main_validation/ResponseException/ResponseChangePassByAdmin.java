package main_validation.ResponseException;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseChangePassByAdmin {
    private String msg;
    private String data;
    private String newPassword;
    private String confirmPassword;
}
