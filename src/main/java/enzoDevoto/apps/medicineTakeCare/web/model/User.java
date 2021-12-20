package enzoDevoto.apps.medicineTakeCare.web.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.UUID;
@Data
@SuperBuilder
@FieldNameConstants

abstract class User {

    private UUID id;
    private String name;
    private String gender;
    private int age;
    private String email;
    private Long phoneNumber;

public User(){}

}
