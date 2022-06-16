package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.CommunityEmailDTO;
import com.openclassrooms.safetynet.dto.FloodDTO;
import com.openclassrooms.safetynet.service.FireStationServiceImpl;
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

import static com.openclassrooms.safetynet.constants.JsonConstants.EMAIL;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class CommunityEmailControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonServiceImpl personService;


    @Test
    public void getFloodListTest() throws Exception {

        // GIVEN :
        CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();
        communityEmailDTO.setEmail(EMAIL);

        List<CommunityEmailDTO> listCommunityEmailDTO = new ArrayList<>();
        listCommunityEmailDTO.add(communityEmailDTO);
        Mockito.when(personService.getCommunityEmailByCity(any(String.class))).thenReturn(listCommunityEmailDTO);

        // WHEN
        // THEN
        mockMvc.perform(get("/communityEmail?city=city")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..email").value(EMAIL));
    }
}