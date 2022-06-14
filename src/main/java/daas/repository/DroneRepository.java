package daas.repository;

import daas.entity.Drone;
import daas.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, String> {

    List<Drone> findAllByState(State state);
}
