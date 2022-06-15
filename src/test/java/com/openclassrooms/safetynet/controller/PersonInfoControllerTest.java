package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.InfoPersonDetailedDTO;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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

import static com.openclassrooms.safetynet.controller.FireStationControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServiceImpl personService;

    @Test
    public void getPersonInfoTest() throws Exception {
        // GIVEN :
        InfoPersonDetailedDTO infoPersonDetailed = new InfoPersonDetailedDTO();
        infoPersonDetailed.setFirstName("firstnametest");
        infoPersonDetailed.setLastName("lastnametest");
        infoPersonDetailed.setAddress("addresstest");
        infoPersonDetailed.setAge(99);
        infoPersonDetailed.setEmail("email@test.fr");

        List<InfoPersonDetailedDTO> listInfoPersonDetailed = new ArrayList<>();
        listInfoPersonDetailed.add(infoPersonDetailed);

        Mockito.when(personService.getPersonByFirstLastName(any(String.class), any(String.class)))
                .thenReturn(listInfoPersonDetailed);

        // WHEN
        // THEN
        mockMvc.perform(get("/personInfo?firstName=firstnametest&lastName=lastnametest")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..firstName").value("firstnametest"))
                .andExpect(jsonPath("$..lastName").value("lastnametest"))
                .andExpect(jsonPath("$..address").value("addresstest"))
                .andExpect(jsonPath("$..age").value(99))
                .andExpect(jsonPath("$..email").value("email@test.fr"));
    }
}