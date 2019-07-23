package lv.autentica.equipmentrequest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lv.autentica.equipmentrequest.equipment.EquipmentDTO;
import lv.autentica.equipmentrequest.equipment.EquipmentEntity;
import lv.autentica.equipmentrequest.equipment.EquipmentRepository;
import lv.autentica.equipmentrequest.equipment.EquipmentService;
import lv.autentica.equipmentrequest.status.EquipmentStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EquipmentController.class)
public class EquipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EquipmentService service;

    @MockBean
    EquipmentRepository repository;

    @Test
    //test should create new Equipment and check can we see status OK
    public void shouldCreateEquipment() throws Exception {

        //create new Object and set object parameters for saving
        EquipmentDTO equipment = new EquipmentDTO();

        equipment.setEquipmentName("canon");
        equipment.setEquipmentType("printer");
        equipment.setComment("message");
        equipment.setEquipmentStatus(EquipmentStatus.USED);

        //Verify that the HTTP status code 200 is returned from URL after when we created new Equipment
        mockMvc.perform(MockMvcRequestBuilders
                .post("/app/add")
                .content(asJsonString(equipment))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    //test should check if list created and showed with all objects
    public void shouldReturnCreatedEquipmentList() throws Exception {

        //create new Object and set object parameters
        EquipmentEntity equipmentResponse = new EquipmentEntity();
        equipmentResponse.setId(1L);
        equipmentResponse.setEquipmentName("canon");
        equipmentResponse.setEquipmentType("printer");
        equipmentResponse.setComment("message");
        equipmentResponse.setEquipmentStatus(EquipmentStatus.USED);

        List<EquipmentEntity> allEquipments = singletonList(equipmentResponse);

        given(service.showEquipmentRequestList()).willReturn(allEquipments);

        //Verify that the HTTP status code 200 is returned from URL after list was returned
        mockMvc.perform(MockMvcRequestBuilders
                .get("/app/equipments")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{ 'equipmentName' : 'canon' " +
                        ", 'equipmentType': 'printer' " +
                        ", 'comment' : 'message' " +
                        ", 'equipmentStatus' : 'USED' }]"));
    }

    @Test
    //with that test we will find equipment from list by Id
    public void findEquipmentFromListById() throws Exception {

        //Verify that the HTTP status code 200 is returned from URL "/app/show/1", after object was found
        mockMvc.perform(MockMvcRequestBuilders
                .get("/app/show/{id}", 1L))
                .andExpect(status().isOk());

        //Verify that the object was found in service by Id
        verify(service, times(1)).findEquipmentByIdFromList(1L);
        verifyNoMoreInteractions(service);
    }


    @Test
    //test check if Object with our Id are deleted from List
    public void deleteEquipmentById() throws Exception{
        doNothing().when(repository).deleteById(1L);

        //Verify that the HTTP status code 200 is returned from URL "/app/delete/1", after deleting
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/app/delete/1"))
                .andExpect(status().isOk());

        //Verify that the object deleted from repository by Id
        verify(repository, times(0)).deleteById(1L);
    }


    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);

            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}