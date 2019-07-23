package lv.autentica.equipmentrequest.equipment;

import lv.autentica.equipmentrequest.status.EquipmentStatus;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.Assert.*;

public class EquipmentMapperTest {

    //if all tests green it mean that all data compliance and work correctly

    @Test
    public void equipmentDTOToEntity() {
        //create new first Object
        EquipmentDTO firstEquipment = new EquipmentDTO();

        //set Object arguments
        firstEquipment.setEquipmentName("Canon");
        firstEquipment.setEquipmentType("Printer");
        firstEquipment.setComment("Delighting You Always");
        firstEquipment.setEquipmentStatus(EquipmentStatus.NEW);
        firstEquipment.setRequestTime(LocalDateTime.of(2019, Month.JULY, 20, 12, 00));

        //create new second Object
        EquipmentDTO secondEquipment = new EquipmentDTO();

        //set Object arguments
        secondEquipment.setEquipmentName("Apple");
        secondEquipment.setEquipmentType("iMac");
        secondEquipment.setComment("Think Different");
        secondEquipment.setEquipmentStatus(EquipmentStatus.USED);
        secondEquipment.setRequestTime(LocalDateTime.of(2019, Month.JUNE, 20, 12, 01));

        //create new mapper
        EquipmentMapper mapper = new EquipmentMapper();
        EquipmentEntity actualEntity = mapper.equipmentDTOToEntity(firstEquipment);
        EquipmentEntity actualSecondEntity = mapper.equipmentDTOToEntity(secondEquipment);

        //data compliance check for first equipment Object
        assertTrue(actualEntity.getEquipmentName().equals("Canon"));
        assertTrue(actualEntity.getEquipmentType().equals("Printer"));
        assertTrue(actualEntity.getComment().equals("Delighting You Always"));
        assertTrue(actualEntity.getEquipmentStatus().equals(EquipmentStatus.NEW));
        assertTrue(actualEntity.getRequestTime().equals(LocalDateTime.of(2019, Month.JULY, 20, 12, 00)));

        //data compliance check for second equipment Object
        assertTrue(actualSecondEntity.getEquipmentName().equals("Apple"));
        assertTrue(actualSecondEntity.getEquipmentType().equals("iMac"));
        assertTrue(actualSecondEntity.getComment().equals("Think Different"));
        assertTrue(actualSecondEntity.getEquipmentStatus().equals(EquipmentStatus.USED));
        assertTrue(actualSecondEntity.getRequestTime().equals(LocalDateTime.of(2019, Month.JUNE, 20, 12, 01)));

    }
}