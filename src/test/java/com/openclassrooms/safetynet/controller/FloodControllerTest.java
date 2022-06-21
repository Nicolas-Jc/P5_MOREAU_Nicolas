package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FloodDTO;
import com.openclassrooms.safetynet.service.FireStationServiceImpl;
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

import static com.openclassrooms.safetynet.constants.JsonTestConstants.ADDRESS;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class FloodControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FireStationServiceImpl fireStationService;


    @Test
    public void getFloodListTest() throws Exception {

        // GIVEN :
        FloodDTO floodDTO = new FloodDTO();
        floodDTO.setAddress(ADDRESS);

        List<FloodDTO> listFloodDTO = new ArrayList<>();
        listFloodDTO.add(floodDTO);
        List<String> listStation = new ArrayList<>();
        listStation.add("1");
        listStation.add("2");
        Mockito.when(fireStationService.getFloodListByStationsList(listStation)).thenReturn(listFloodDTO);

        // WHEN //THEN
        mockMvc.perform(
                        get("/flood?stations=1,2").contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..address").value(ADDRESS));
    }
}