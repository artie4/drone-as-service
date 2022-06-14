package daas.repository;

import daas.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
}
