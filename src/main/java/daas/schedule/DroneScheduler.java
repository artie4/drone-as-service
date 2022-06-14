package daas.schedule;

import daas.entity.Drone;
import daas.repository.DroneRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class DroneScheduler {

    @Value("${drone.battery.min-for-loading}")
    private int minBatteryLevel;

    private final DroneRepository droneRepository;

    @Scheduled(cron = "0 * * * * *")
    public void checkDronesBatteryLevel() {
        droneRepository.findAll().forEach(
                drone -> {
                    if (drone.getBatteryCapacity() < minBatteryLevel) {
                        log.warn("Low battery level of drone: {}", drone);
                    }
                }
        );
    }
}
