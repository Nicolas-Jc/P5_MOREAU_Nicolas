package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.model.MedicalRecord;

import com.openclassrooms.safetynet.service.MedicalRecordServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static com.openclassrooms.safetynet.constants.JsonConstants.*;
import static com.openclassrooms.safetynet.controller.FireStationControllerTest.asJsonString;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicalRecordServiceImpl medicalRecordService;

    @MockBean
    private MedicalRecord medicalRecord;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    //public Date testDate = new Date();

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    // dateFormat.(TimeZone.getTimeZone("UTC"));
    Date birthDate = dateFormat.parse("01/01/2000");
    List<String> medicationsCsteList;
    List<String> allergiesCsteList;

    public MedicalRecordControllerTest() throws ParseException {
    }

    @BeforeEach
    public void beforeEach() {

        medicationsCsteList = new ArrayList<>(Arrays.asList("aznol:350mg", "hydrapermazol:500mg", "terazine:750mg"));
        allergiesCsteList = new ArrayList<>(Arrays.asList("aznol", "xilliathal", "peanut"));

        medicalRecord = new MedicalRecord();
        medicalRecord.setFirstName(FIRST_NAME);
        medicalRecord.setLastName(LAST_NAME);
        medicalRecord.setMedications(medicationsCsteList);
        medicalRecord.setAllergies(allergiesCsteList);
    }

    @Test
    public void addMedicalRecordTest() throws Exception {

        // GIVEN :
        //Date birthDate = dateFormat.parse("01/01/2000");
        medicalRecord.setBirthdate(birthDate);

        Mockito.when(medicalRecordService.add(any(MedicalRecord.class))).thenReturn(medicalRecord);
        System.out.println(medicalRecord.toString());

        // WHEN //THEN
        mockMvc.perform(post("/medicalRecord")
                        .content(asJsonString(new MedicalRecord(FIRST_NAME, LAST_NAME, birthDate, medicationsCsteList,
                                allergiesCsteList)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }

    @Test
    public void modifyMedicalRecordTest() throws Exception {

        // GIVEN :
        //Date birthDate = dateFormat.parse("01/01/2000");
        medicalRecord.setBirthdate(birthDate);

        Mockito.when(medicalRecordService.modify(any(MedicalRecord.class))).thenReturn(medicalRecord);
        System.out.println(medicalRecord.toString());

        // WHEN //THEN
        mockMvc.perform(put("/medicalRecord")
                        .content(asJsonString(new MedicalRecord(FIRST_NAME, LAST_NAME, birthDate, medicationsCsteList,
                                allergiesCsteList)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(FIRST_NAME))
                .andExpect(jsonPath("$.lastName").value(LAST_NAME));
    }

    @Test
    public void deleteMedicalRecordTest() throws Exception {

        // GIVEN :
        medicalRecord.setBirthdate(birthDate);

        Mockito.when(medicalRecordService.delete(any(String.class), any(String.class))).thenReturn(true);

        // WHEN //THEN
        mockMvc.perform(delete("/medicalRecord?firstName=" + FIRST_NAME + "&lastName=" + LAST_NAME)
                        .content(asJsonString(new MedicalRecord(FIRST_NAME, LAST_NAME, birthDate, medicationsCsteList,
                                allergiesCsteList)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}