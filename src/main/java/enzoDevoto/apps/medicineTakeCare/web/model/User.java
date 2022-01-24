package enzoDevoto.apps.medicineTakeCare.web.model;

import enzoDevoto.apps.medicineTakeCare.web.utils.PatientConstants;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.NotBlank;



@Data
@SuperBuilder
@FieldNameConstants

abstract class User {

    @NumberFormat
    @NotNull
    @ApiModelProperty(value = "User id")
    private Long id;
    @ApiModelProperty(value = "User name")
    private String name;
    @ApiModelProperty(value = "User Second name")
    private String secondName;
    @ApiModelProperty(value = "User gender")
    private String gender;
    @ApiModelProperty(value = "User password")
    @NotNull
    @NotBlank
    private String password;
    @ApiModelProperty(value = "User username")
    @NotNull
    @NotBlank
    private String username;
    @ApiModelProperty(value = "User age")
    @NumberFormat
    @NotNull
    private int age;
    @ApiModelProperty(value = "User email")
    @NotNull
    @NotEmpty
    @Email(message = "Should not contain ilegal characters. Email format is not correct." , regexp = PatientConstants.EMAIL_VALIDATION)
    private String email;
    @ApiModelProperty(value = "User Phone number")
    @NotNull(message = "Phone number is null.")
    @Pattern(regexp = PatientConstants.TEN_DIGITS_PHONE_VALIDATION, message = "Phone number format is not valid.")
    private String phoneNumber;
    @ApiModelProperty(value = "User address")
    private String address;

public User(){}

}


