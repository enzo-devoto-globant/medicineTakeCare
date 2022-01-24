package enzoDevoto.apps.medicineTakeCare.web.model;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "Patient Description")
    private String description;
    @ApiModelProperty(value = "Patient time of evaluation")
    private Date timeOfEvaluation;
    @ApiModelProperty(value = "Patient is in critical situation?")
    private boolean isCritical;
    @ApiModelProperty(value = "Patient Doctor")
    private DoctorDto myDoctor;

}


