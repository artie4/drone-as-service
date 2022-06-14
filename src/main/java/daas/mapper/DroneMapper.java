package daas.mapper;

import daas.entity.Drone;
import daas.web.request.DroneDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DroneMapper {

    Drone toDrone(DroneDto droneDto);

    DroneDto toDroneDto(Drone drone);

}
