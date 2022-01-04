package enzoDevoto.apps.medicineTakeCare.web.model;

import lombok.Data;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@FieldNameConstants

abstract class User {

    private Long id;
    private String name;
    private String gender;
    private int age;
    private String email;
    private Long phoneNumber;
    private String address;

public User(){}

}
