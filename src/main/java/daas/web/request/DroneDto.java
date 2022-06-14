package daas.web.request;

import daas.model.DroneType;
import daas.model.State;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
@Data
public class DroneDto {

    @Size(max = 100)
    @ApiModelProperty(example = "MIDDLEWEIGHT-300-2020")
    private String serialNumber;

    @NotNull
    @ApiModelProperty(example = "MIDDLEWEIGHT")
    private DroneType model;

    @NotNull
    @Positive
    @Max(value = 500)
    @ApiModelProperty(example = "400")
    private Integer weightLimit;

    @PositiveOrZero
    @Max(value = 100)
    @ApiModelProperty(example = "99")
    private Integer batteryCapacity;

    @ApiModelProperty(example = "IDLE")
    private State state;
}
