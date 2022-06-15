package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.PhoneAlertDTO;

import com.openclassrooms.safetynet.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class AlertControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServiceImpl personService;

    @Test
    public void ChildAlertTest() throws Exception {

        // GIVEN
        ChildAlertDTO childAlertDTO = new ChildAlertDTO();
        childAlertDTO.setFirstName("testFirstName");
        childAlertDTO.setLastName("testLastName");
        childAlertDTO.setAge(12);
        List<ChildAlertDTO> listChildAlertDTO = new ArrayList<>();
        listChildAlertDTO.add(childAlertDTO);
        Mockito.when(personService.getChildAlertByAddress(any(String.class))).thenReturn(listChildAlertDTO);

        // WHEN,THEN
        mockMvc.perform(get("/childAlert?address=adresstest")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("testFirstName"))
                .andExpect(jsonPath("$..lastName").value("testLastName"))
                .andExpect(jsonPath("$..age").value(12));

    }

    @Test
    public void PhoneAlertTest() throws Exception {

        // GIVEN :
        PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
        phoneAlertDTO.setPhone("phonetest");
        List<PhoneAlertDTO> listPhoneAlertDTO = new ArrayList<>();
        listPhoneAlertDTO.add(phoneAlertDTO);
        Mockito.when(personService.getPhoneAlertByFirestation(any(String.class))).thenReturn(listPhoneAlertDTO);

        // WHEN //THEN
        mockMvc.perform(get("/phoneAlert?firestation=1")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..phone").value("phonetest"));
    }

}
