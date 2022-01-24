package enzoDevoto.apps.medicineTakeCare.web.model;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@ApiModel(description = "Doctor model information")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor

public class DoctorDto extends User{
    @ApiModelProperty(value = "Doctor specialty Area")
    private String specialtyArea;
    @ApiModelProperty(value = "Doctor description")
    private String description;
    @ApiModelProperty(value = "Doctor rating")
    private double rate;
    @ApiModelProperty(value = "Doctor price")
    private long price;
    @ApiModelProperty(value = "List of patients of this doctor")
    private String myPatients;

}
