package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static com.openclassrooms.safetynet.constants.JsonTestConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class FireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServiceImpl personService;


    @Test
    public void getFireTest() throws Exception {

        // GIVEN :
        FireDTO fireDTO = new FireDTO();
        fireDTO.setFirstName(FIRST_NAME);
        fireDTO.setLastName(LAST_NAME);
        fireDTO.setPhone(PHONE);
        fireDTO.setAge(AGE);
        fireDTO.setStation(STATION);

        List<FireDTO> listFireDTO = new ArrayList<>();
        listFireDTO.add(fireDTO);
        Mockito.when(personService.getPersonByAddress(any(String.class))).thenReturn(listFireDTO);

        // WHEN // THEN
        mockMvc.perform(get("/fire?address=addresstest").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$..lastName").value(LAST_NAME))
                .andExpect(jsonPath("$..phone").value(PHONE))
                .andExpect(jsonPath("$..age").value(AGE))
                .andExpect(jsonPath("$..station").value(STATION));
    }
}