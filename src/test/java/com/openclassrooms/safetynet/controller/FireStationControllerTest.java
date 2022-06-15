package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.dto.FireStationPerimeterDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.FireStationServiceImpl;
import com.openclassrooms.safetynet.service.MedicalRecordServiceImpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationServiceImpl fireStationService;

    @MockBean
    private FireStation fireStation;


    @Test
    public void addFireStationTest() throws Exception {

        //GIVEN :
        fireStation = new FireStation();
        fireStation.setStation("77");
        fireStation.setAddress("addressadded");
        Mockito.when(fireStationService.add(any(FireStation.class))).thenReturn(fireStation);

        //WHEN
        // THEN
        mockMvc.perform(post("/firestation")
                        .content(asJsonString(new FireStation("stationtest", "addresstest")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("77"))
                .andExpect(jsonPath("$.address").value("addressadded"));

    }

    @Test
    public void getFireStationCoverageTest() throws Exception {

        //GIVEN :
        FireStationPerimeterDTO FireStationPerimeterDTO = new FireStationPerimeterDTO();
        FireStationPerimeterDTO.setAdultCount(5);
        FireStationPerimeterDTO.setChildCount(10);
        Mockito.when(fireStationService.fireStationPersonsScope(any(String.class))).thenReturn(FireStationPerimeterDTO);

        //WHEN
        // THEN
        mockMvc.perform(get("/firestation?stationNumber=2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..adultCount").value(5))
                .andExpect(jsonPath("$..childCount").value(10));

    }

    @Test
    public void deleteFireStationTest() throws Exception {

        //GIVEN :
        fireStation = new FireStation();
        fireStation.setAddress("addresstest");
        fireStation.setStation("99");
        List<FireStation> listFireStation = new ArrayList<>();
        listFireStation.add(fireStation);
        Mockito.when(fireStationService.delete(any(FireStation.class))).thenReturn(listFireStation);

        //WHEN
        // THEN
        mockMvc.perform(delete("/firestation")
                        .content(asJsonString(new FireStation("addresstestdelete", "stationtest")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..station").value("99"))
                .andExpect(jsonPath("$..address").value("addresstest"));

    }

    @Test
    public void modifyFireStationTest() throws Exception {

        //GIVEN :
        fireStation = new FireStation();
        fireStation.setStation("88");
        fireStation.setAddress("addressmodified");
        Mockito.when(fireStationService.modify(any(FireStation.class))).thenReturn(fireStation);

        //WHEN
        // THEN
        mockMvc.perform(put("/firestation")
                        .content(asJsonString(new FireStation("stationtest", "addresstest")))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value("88"))
                .andExpect(jsonPath("$.address").value("addressmodified"));

    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
