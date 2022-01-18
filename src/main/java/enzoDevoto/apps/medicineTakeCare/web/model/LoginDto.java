package enzoDevoto.apps.medicineTakeCare.web.model;

import lombok.Data;

@Data
public class LoginDto {
    private String usernameOrEmail;
    private String password;
}
