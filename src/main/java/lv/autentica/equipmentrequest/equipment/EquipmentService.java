package lv.autentica.equipmentrequest.equipment;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EquipmentService {

    EquipmentMapper equipmentMapper;
    EquipmentRepository equipmentRepository;

    public EquipmentEntity addNewEquipmentToList(EquipmentDTO equipmentDTO) {
        EquipmentEntity equipmentEntity = equipmentMapper.equipmentDTOToEntity(equipmentDTO);
        return equipmentRepository.save(equipmentEntity);
    }

    public EquipmentEntity findEquipmentByIdFromList(Long id) {
        return equipmentRepository.findEquipmentEntityById(id);
    }

    public String deleteEquipmentRequestById(Long id) {
        equipmentRepository.deleteById(id);
        return "Equipment request with id" + id + " has been deleted successfully";
    }

    public List<EquipmentEntity> showEquipmentRequestList() {
        return equipmentRepository.findAll();
    }

}