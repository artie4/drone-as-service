package daas.mapper;

import daas.entity.Medication;
import daas.web.request.MedicationDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicationMapper {

    Medication toMedication(MedicationDto medicationDto);

    MedicationDto toMedicationDto(Medication medication);

}
