package daas.web.controller;

import daas.entity.Medication;
import daas.service.DroneService;
import daas.web.request.DroneDto;
import daas.web.request.MedicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/drones")
@RequiredArgsConstructor
public class DroneController {

    private final DroneService droneService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public DroneDto registerDrone(@RequestBody DroneDto droneDto) {
        return droneService.registerDrone(droneDto);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{number}/medication")
    public DroneDto loadMedication(
            @PathVariable("number") String droneSerialNumber,
            @RequestBody MedicationDto medicationDto) {
        return droneService.loadMedication(droneSerialNumber, medicationDto);
    }

    @GetMapping("/{number}/medication")
    public MedicationDto getMedicationByDrone(@PathVariable("number") String droneSerialNumber) {
        return droneService.getMedicationByDrone(droneSerialNumber);
    }

    @GetMapping
    public List<DroneDto> getAvailableDrones() {
        return droneService.getAvailableDrones();
    }

    @GetMapping("/{number}/battery")
    public Integer getBatteryLevelByDrone(@PathVariable("number") String droneSerialNumber) {
        return droneService.getBatteryLevelByDrone(droneSerialNumber);
    }
}
