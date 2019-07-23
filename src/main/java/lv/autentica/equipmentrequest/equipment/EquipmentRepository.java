package lv.autentica.equipmentrequest.equipment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface EquipmentRepository extends JpaRepository<EquipmentEntity, Long> {

    EquipmentEntity findEquipmentEntityByEquipmentName(String equipmentName);

    EquipmentEntity findEquipmentEntityById(Long id);

}