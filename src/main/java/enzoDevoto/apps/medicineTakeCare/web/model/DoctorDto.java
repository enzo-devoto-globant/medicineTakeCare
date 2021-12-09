package enzoDevoto.apps.medicineTakeCare.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class DoctorDto extends User{

    private String speciality;
    private String description;
    private double rate;
    private long price;

}
