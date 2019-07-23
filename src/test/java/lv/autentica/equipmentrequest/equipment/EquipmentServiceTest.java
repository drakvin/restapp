package lv.autentica.equipmentrequest.equipment;

import lv.autentica.equipmentrequest.status.EquipmentStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.mock.web.reactive.function.server.MockServerRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EquipmentServiceTest {

    @Autowired
    EquipmentService service;

    @Autowired
    EquipmentRepository repository;

    @MockBean
    EquipmentRepository mockRepo;

    @Test
    //should save New equipment to Data Base
    public void shouldSaveNewEquipmentToDataBase() {

        //create new equipment Object
        EquipmentDTO equipment = new EquipmentDTO();

        //set Object arguments
        equipment.setEquipmentName("Canon");
        equipment.setEquipmentType("Printer");
        equipment.setComment("Delighting You Always");
        equipment.setEquipmentStatus(EquipmentStatus.NEW);
        equipment.setRequestTime(LocalDateTime.of(2019, Month.APRIL, 18, 14, 14));

        //call service method, which save new object to DataBase
        service.addNewEquipmentToList(equipment);

        EquipmentEntity equipmentEntity = repository.findEquipmentEntityByEquipmentName(equipment.getEquipmentName());

        //check if the Object is saved to the database
        assertNotNull(equipmentEntity);
    }

    @Test
    //test check if object with Id deleted from list
    public void shouldDeleteEquipmentRequestByIdFromList() {

        final EquipmentEntity entity = new EquipmentEntity();
        entity.setId(1L);
        entity.setEquipmentName("iMac");

        service.deleteEquipmentRequestById(entity.getId());

        Mockito.verify(mockRepo, times(0)).delete(entity);
    }

    @Test
    //should show all equipments from saved list
    public void showEquipmentRequestList() {

        EquipmentDTO equipment = new EquipmentDTO();

        //set Object arguments from DTO
        equipment.setEquipmentName("canon");
        equipment.setEquipmentType("printer");
        equipment.setComment("Delighting You Always");
        equipment.setEquipmentStatus(EquipmentStatus.NEW);

        //call service method, which save new object to DataBase
        service.addNewEquipmentToList(equipment);

        EquipmentEntity response = service.showEquipmentRequestList().get(0);

        //check DTO parameters equals Entity parameters (from jpaRepository list)
        assertEquals(equipment.getEquipmentName(), (response.getEquipmentName()));
        assertEquals(equipment.getEquipmentType(), (response.getEquipmentType()));
        assertEquals(equipment.getEquipmentStatus(), (response.getEquipmentStatus()));
        assertEquals(equipment.getComment(), (response.getComment()));
    }
}