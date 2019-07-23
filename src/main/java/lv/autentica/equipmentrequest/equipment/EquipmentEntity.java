package lv.autentica.equipmentrequest.equipment;

import lombok.Getter;
import lombok.Setter;
import lv.autentica.equipmentrequest.status.EquipmentStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)//id auto generation in table
    private Long id;

    @Size(min = 3, max = 20) // name must be from 3 to 2o characters
    private String equipmentName;

    @Size(min = 3, max = 20)// type must be from 3 to 2o characters
    private String equipmentType;
    private String comment;
    private EquipmentStatus equipmentStatus;
    private LocalDateTime requestTime;
}
