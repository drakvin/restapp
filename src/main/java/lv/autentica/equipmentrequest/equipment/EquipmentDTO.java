package lv.autentica.equipmentrequest.equipment;

import lombok.Getter;
import lombok.Setter;
import lv.autentica.equipmentrequest.status.EquipmentStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class EquipmentDTO {

    private String equipmentName;
    private String equipmentType;
    private String comment;
    private EquipmentStatus equipmentStatus;
    private LocalDateTime requestTime;

}