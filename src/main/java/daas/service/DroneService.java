package daas.service;

import daas.entity.Delivery;
import daas.entity.Drone;
import daas.exception.InvalidDroneStateException;
import daas.exception.LowBatteryLevelException;
import daas.exception.NotFoundException;
import daas.exception.TooHeavyLoadException;
import daas.entity.Medication;
import daas.mapper.DroneMapper;
import daas.mapper.MedicationMapper;
import daas.model.State;
import daas.repository.DeliveryRepository;
import daas.repository.DroneRepository;
import daas.repository.MedicationRepository;
import daas.web.request.DroneDto;
import daas.web.request.MedicationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DroneService {

    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;
    private final DeliveryRepository deliveryRepository;
    private final DroneMapper droneMapper;
    private final MedicationMapper medicationMapper;

    @Value("${drone.battery.min-for-loading}")
    private int minBatteryLevel;


    public DroneDto registerDrone(DroneDto droneDto) {
        Drone drone = droneMapper.toDrone(droneDto);
        return droneMapper.toDroneDto(droneRepository.save(drone));
    }

    @Transactional
    public DroneDto loadMedication(String droneSerialNumber, MedicationDto medicationDto) {
        Drone drone = getDroneByNumberOrThrow(droneSerialNumber);
        checkStatusOrThrow(drone.getState(), State.IDLE);
        checkWeightOrThrow(drone.getWeightLimit(), medicationDto.getWeight());
        checkBatteryOrThrow(drone.getBatteryCapacity(), minBatteryLevel);
        drone.setState(State.LOADING);
        Medication medication = medicationMapper.toMedication(medicationDto);
        droneRepository.save(drone);
        Medication savedMed = medicationRepository.save(medication);
        Delivery delivery = createDelivery(drone, savedMed);
        deliveryRepository.save(delivery);
        return droneMapper.toDroneDto(drone);
    }

    public MedicationDto getMedicationByDrone(String droneSerialNumber) {
        List<Delivery> deliveries = deliveryRepository.findAllByDroneId(droneSerialNumber);
        Delivery delivery = deliveries.stream()
                .filter(d -> d.getEndDate() == null)
                .max(Comparator.comparing(Delivery::getStartDate))
                .orElseThrow(NotFoundException::new);
        Medication medication = getMedicationByIdOrThrow(delivery.getMedicationId());
        return medicationMapper.toMedicationDto(medication);
    }

    public List<DroneDto> getAvailableDrones() {
        return droneRepository.findAllByState(State.IDLE)
                .stream()
                .filter(d -> d.getBatteryCapacity() >= minBatteryLevel)
                .map(droneMapper::toDroneDto)
                .collect(Collectors.toList());
    }

    public Integer getBatteryLevelByDrone(String droneSerialNumber) {
        return getDroneByNumberOrThrow(droneSerialNumber).getBatteryCapacity();
    }

    private Drone getDroneByNumberOrThrow(String droneSerialNumber) {
        return droneRepository.findById(droneSerialNumber).orElseThrow(NotFoundException::new);
    }

    private Medication getMedicationByIdOrThrow(Long medId) {
        return medicationRepository.findById(medId).orElseThrow(NotFoundException::new);
    }

    private void checkStatusOrThrow(State actual, State expected) {
        if (actual != expected) {
            throw new InvalidDroneStateException();
        }
    }

    private void checkWeightOrThrow(Integer weightLimit, Integer weight) {
        if (weightLimit < weight) {
            throw new TooHeavyLoadException();
        }
    }

    private void checkBatteryOrThrow(Integer batteryCapacity, Integer minBatteryLevel) {
        if (batteryCapacity < minBatteryLevel) {
            throw new LowBatteryLevelException();
        }
    }

    private Delivery createDelivery(Drone drone, Medication medication) {
        return new Delivery()
                .setDroneId(drone.getSerialNumber())
                .setMedicationId(medication.getId());
    }
}
