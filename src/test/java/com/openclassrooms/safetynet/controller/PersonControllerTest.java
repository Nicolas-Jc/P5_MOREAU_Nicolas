package com.openclassrooms.safetynet.controller;

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


import static com.openclassrooms.safetynet.constants.JsonConstants.*;
import static com.openclassrooms.safetynet.controller.FireStationControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServiceImpl personService;

    @MockBean
    private Person person;

    @BeforeEach
    public void beforeEach() {

        person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
        person.setAddress(ADDRESS);
        person.setCity(CITY);
        person.setZip(ZIP);
        person.setPhone(PHONE);
        person.setEmail(EMAIL);
    }

    @Test
    public void addPersonTest() throws Exception {

        // GIVEN
        Mockito.when(personService.add(any(Person.class))).thenReturn(person);
        // WHEN
        // THEN
        mockMvc.perform(post("/person")
                        .content(asJsonString(new Person(FIRST_NAME, LAST_NAME, ADDRESS, CITY,
                                ZIP, PHONE, EMAIL)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }

    @Test
    public void modifyPersonTest() throws Exception {

        // GIVEN
        Mockito.when(personService.modify(any(Person.class))).thenReturn(person);
        // WHEN
        // THEN
        mockMvc.perform(put("/person")
                        .content(asJsonString(new Person(FIRST_NAME, LAST_NAME, ADDRESS, CITY,
                                ZIP, PHONE, EMAIL)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void deletePersonTest() throws Exception {

        // GIVEN
        Mockito.when(personService.delete(any(String.class), any(String.class))).thenReturn(true);
        // WHEN
        // THEN
        mockMvc.perform(delete("/person?firstname=" + FIRST_NAME + "&lastname=" + LAST_NAME)
                        .content(asJsonString(new Person(FIRST_NAME, LAST_NAME, ADDRESS, CITY,
                                ZIP, PHONE, EMAIL)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(content().string("Deleted person : " + FIRST_NAME + " " + LAST_NAME))
                .andExpect(status().isOk());
    }
}