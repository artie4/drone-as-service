package daas.web.request;

import daas.utils.RegexPatterns;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Validated
@Data
public class MedicationDto {

    @NotEmpty
    @Pattern(regexp = RegexPatterns.MEDICATION_NAME)
    private String name;
    @Min(value = 1)
    @Max(value = 500)
    private Integer weight;
    @NotEmpty
    @Pattern(regexp = RegexPatterns.MEDICATION_CODE)
    private String code;
    private byte[] image;
}
