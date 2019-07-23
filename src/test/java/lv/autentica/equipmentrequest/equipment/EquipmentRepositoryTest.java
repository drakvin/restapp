package lv.autentica.equipmentrequest.equipment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@DataJpaTest

public class EquipmentRepositoryTest {

    @MockBean
    EquipmentRepository repository;

    @Test
    //check if before testing Repository are empty (without arguments)
    public void repositoryShouldBeEmpty() {
        assertEquals(repository.count(), 0);
    }

    @Test
    //check equipment request from List by Id
    public void shouldFindByIdFromList() {

        //create new Object and set id
        EquipmentEntity equip = new EquipmentEntity();
        equip.setId(1L);

        //check if equipment from repository with Id = 1, equals with equip with id = 1
        when(repository.findEquipmentEntityById(1L)).thenReturn(equip);
    }

    @Test
    //check equipment request from List by Equipment name
    public void shouldFindByNameFromList() {

        //create new Object and set Name
        EquipmentEntity equipName = new EquipmentEntity();
        equipName.setEquipmentName("canon");

        //check if equipment from repository with Name = "canon", equals with equipName with name = "canon"
        when(repository.findEquipmentEntityByEquipmentName("canon")).thenReturn(equipName);
    }
}