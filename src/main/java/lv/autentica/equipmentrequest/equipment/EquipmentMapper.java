package lv.autentica.equipmentrequest.equipment;

import org.springframework.stereotype.Component;

@Component
public class EquipmentMapper {

    public EquipmentEntity equipmentDTOToEntity(EquipmentDTO equipmentDTO){

        //create new equipment Object
        EquipmentEntity equipment = new EquipmentEntity();

        //set arguments from DTO (Data Transfer Object) to Entity (POJO-class, which works with DataBAse)
        equipment.setEquipmentName(equipmentDTO.getEquipmentName());
        equipment.setEquipmentType(equipmentDTO.getEquipmentType());
        equipment.setEquipmentStatus(equipmentDTO.getEquipmentStatus());
        equipment.setComment(equipmentDTO.getComment());
        equipment.setRequestTime(equipmentDTO.getRequestTime());

        return equipment;
    }
}
