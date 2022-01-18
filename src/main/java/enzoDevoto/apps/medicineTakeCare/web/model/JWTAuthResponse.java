package enzoDevoto.apps.medicineTakeCare.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JWTAuthResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JWTAuthResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
