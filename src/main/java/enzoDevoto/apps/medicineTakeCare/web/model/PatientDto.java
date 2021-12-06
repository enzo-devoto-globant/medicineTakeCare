package enzoDevoto.apps.medicineTakeCare.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto extends User{

    private String description;
    private Date timeOfEvaluation;
    private boolean isCritical;

}


