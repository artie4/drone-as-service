package daas.entity;

import daas.model.DroneType;
import daas.model.State;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Drone implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String serialNumber;
    @Enumerated(EnumType.STRING)
    private DroneType model;
    private Integer weightLimit;
    private Integer batteryCapacity;
    @Enumerated(EnumType.STRING)
    private State state;

    static final long serialVersionUID = 1202889039713657510L;
}
