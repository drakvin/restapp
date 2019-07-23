package lv.autentica.equipmentrequest.controller;

import lombok.AllArgsConstructor;
import lv.autentica.equipmentrequest.equipment.EquipmentDTO;
import lv.autentica.equipmentrequest.equipment.EquipmentEntity;
import lv.autentica.equipmentrequest.equipment.EquipmentService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/app")
public class EquipmentController {

    EquipmentService equipmentService;

    @PostMapping("/add")
    public EquipmentEntity addNewEquipmentToList(@RequestBody EquipmentDTO equipmentDTO) {
        return equipmentService.addNewEquipmentToList(equipmentDTO);
    }

    @GetMapping("/equipments")
    public Iterable<EquipmentEntity> showEquipmentRequest() {
        return equipmentService.showEquipmentRequestList();
    }

    @GetMapping("/show/{id}")
    public EquipmentEntity findEquipmentFromListById(@PathVariable("id") Long id) {
        return equipmentService.findEquipmentByIdFromList(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable("id") Long id) {
        equipmentService.deleteEquipmentRequestById(id);
    }

}
