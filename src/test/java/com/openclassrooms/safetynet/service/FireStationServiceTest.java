package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class FireStationServiceTest {

    @MockBean
    FireStationDAO fireStationDAO;

    @MockBean
    PersonService personService;

    @MockBean
    CalculateFonction calculateFonction;

    @MockBean
    PersonDAO personDAO;

    @MockBean
    MedicalRecordServiceImpl medicalRecordService;

    @Autowired
    FireStationService fireStationService;


    @Test
    public void getFloodListByStationListTest() throws Exception {
        // GIVEN
        List<String> listStations = new ArrayList<>();
        String station1 = "1";
        String station2 = "2";
        listStations.add(station1);
        listStations.add(station2);

        List<String> listAddress = new ArrayList<>();
        String address1 = "address1";
        String address2 = "address2";
        listAddress.add(address1);
        listAddress.add(address2);
        Mockito.when(fireStationDAO.getFireStationAdressByStation(any(String.class))).thenReturn(listAddress);

        List<Person> listPersons = new ArrayList<>();
        Person person1 = new Person("firstName1", "lastName1", "", "City1", "", "", "email1@test.fr");
        Person person2 = new Person("firstName2", "lastName2", "", "City2", "", "", "email2@test.fr");
        listPersons.add(person1);
        listPersons.add(person2);
        Mockito.when(personDAO.findPersonByAdress(any(String.class))).thenReturn(listPersons);

        String Date = "01/01/2000";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse(Date);
        List<String> medications = new ArrayList<String>();
        medications.add("medication1");
        List<String> allergies = new ArrayList<String>();
        allergies.add("allergie1");
        MedicalRecord medicalRecord = new MedicalRecord("FirstName1", "LastName1", birthDate, medications,
                allergies);
        Mockito.when(medicalRecordService.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecord);
        // WHEN
        List<FloodDTO> listFloodDTO = fireStationService.getFloodListByStationsList(listStations);
        // THEN
        assertThat(listFloodDTO.size()).isEqualTo(4);

    }

    @Test
    public void getFireStationPersonsScopeTest() throws Exception {
        // GIVEN
        List<String> listAddress = new ArrayList<>();
        listAddress.add("99 Street Test FindPersonByAdress");
        Mockito.when(fireStationDAO.getFireStationAdressByStation(any(String.class))).thenReturn(listAddress);

        List<Person> listPerson = new ArrayList<>();
        Person person1 = new Person("firstName1", "lastName1", "99 Street Test FindPersonByAdress", "City", "", "", "email1@test.fr");
        Person person2 = new Person("firstName2", "lastName2", "99 Street Test FindPersonByAdress", "City", "", "", "email2@test.fr");
        listPerson.add(person1);
        listPerson.add(person2);
        Mockito.when(personDAO.getAllPersons()).thenReturn(listPerson);

        String Date = "01/01/1990";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse(Date);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");
        MedicalRecord medicalRecord = new MedicalRecord("Firstnametest3", "Lastnametest3", birthDate, medications,
                allergies);
        Mockito.when(medicalRecordService.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecord);

        // WHEN // THEN
        assertThat(fireStationService.getFireStationPersonsScope("station").getChildCount()).isEqualTo(2);
        //assertThat(fireStationService.getFireStationPersonsScope("station").getAdultCount()).isEqualTo(0);

    }
}
