package enzoDevoto.apps.medicineTakeCare.web.model;

import enzoDevoto.apps.medicineTakeCare.web.utils.PatientConstants;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


@Data
@SuperBuilder
@FieldNameConstants

abstract class User {
    @NumberFormat
    @NotNull
    private Long id;

    private String name;
    private String gender;

    @NumberFormat
    @NotNull
    private int age;

    @NotNull
    @NotEmpty
    @Email(message = "Should not contain ilegal characters. Email format is not correct." , regexp = PatientConstants.EMAIL_VALIDATION)
    private String email;


    @NotNull(message = "Phone number is null.")
    @Pattern(regexp = PatientConstants.TEN_DIGITS_PHONE_VALIDATION, message = "Phone number format is not valid.")
    private String phoneNumber;
    private String address;

public User(){}

}


