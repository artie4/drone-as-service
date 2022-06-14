package daas.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class Delivery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String droneId;
    private Long medicationId;
    private LocalDateTime startDate = LocalDateTime.now() ;
    private LocalDateTime endDate;
}
