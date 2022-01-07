package enzoDevoto.apps.medicineTakeCare.web.exception;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MedicineTakeCareAPIException extends RuntimeException{

    private HttpStatus status;
    private String message;

    public MedicineTakeCareAPIException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
