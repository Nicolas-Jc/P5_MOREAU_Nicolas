package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.dao.MedicalRecordsDAO;
import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
public class PersonServiceTest {

    @MockBean
    PersonDAO personDAO;

    @MockBean
    FireStationDAO fireStationDAO;

    @MockBean
    MedicalRecordsDAO medicalRecordsDAO;

    @MockBean
    CalculateFonction calculateFonction;

    @MockBean
    FireStationServiceImpl fireStationService;

    @MockBean
    MedicalRecordServiceImpl medicalRecordService;

    @Autowired
    PersonService personService;


    @Test
    void getCommunityEmailByCityTest() {
        // GIVEN
        List<Person> persons = new ArrayList<>();
        Person person1 = new Person("firstName1", "lastName1", "adress1", "CityToFind", "", "", "email1@test.fr");
        Person person2 = new Person("firstName2", "lastName2", "adress2", "CityToFind", "", "", "email2@test.fr");
        persons.add(person1);
        persons.add(person2);
        Mockito.when(personDAO.getAllPersons()).thenReturn(persons);
        // WHEN
        List<CommunityEmailDTO> communityEmailList = personService.getCommunityEmailByCity("CityToFind");
        // THEN
        assertThat(communityEmailList.size()).isEqualTo(2);
        assertThat(communityEmailList.get(0).getEmail()).isEqualTo(person1.getEmail());
        assertThat(communityEmailList.get(1).getEmail()).isEqualTo(person2.getEmail());
    }

    @Test
    void getPhoneAlertByFirestationTest() {
        // GIVEN
        List<String> listAdress = new ArrayList<>();
        listAdress.add("adresseToFind1");
        listAdress.add("adresseToFind2");
        Mockito.when(fireStationDAO.getFireStationAdressByStation(any(String.class))).thenReturn(listAdress);

        List<Person> listPersons = new ArrayList<>();
        Person person1 = new Person("firstName1", "lastName1", "adresseToFind1", "City", "", "333-444-5555", "email1@test.fr");
        Person person2 = new Person("firstName2", "lastName2", "adresseToFind2", "City", "", "111-222-3333", "email2@test.fr");
        listPersons.add(person1);
        listPersons.add(person2);
        Mockito.when(personDAO.getAllPersons()).thenReturn(listPersons);
        // WHEN
        List<PhoneAlertDTO> listPhoneAlert = personService.getPhoneAlertByFirestation("2");
        // THEN
        assertThat(listPhoneAlert.size()).isEqualTo(2);
        assertThat(listPhoneAlert.get(0).getPhone()).isEqualTo("333-444-5555");
        assertThat(listPhoneAlert.get(1).getPhone()).isEqualTo("111-222-3333");
    }

    @Test
    void getPersonByFirstLastNameTest() throws ParseException {
        // GIVEN
        List<Person> listPersons = new ArrayList<>();
        Person person1 = new Person("firstName1", "lastName1", "adress1", "City1", "", "", "email1@test.fr");
        Person person2 = new Person("firstName1", "lastName1", "adress2", "City2", "", "", "email2@test.fr");
        Person person3 = new Person("firstName2", "lastName2", "adress3", "City2", "", "", "email3@test.fr");
        listPersons.add(person1);
        listPersons.add(person2);
        listPersons.add(person3);

        Mockito.when(personDAO.findPersonByFirstNameAndLastName(any(String.class), any(String.class)))
                .thenReturn(listPersons);

        String Date = "01/01/2000";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse(Date);
        List<String> medications = new ArrayList<String>();
        medications.add("medication1");
        List<String> allergies = new ArrayList<String>();
        allergies.add("allergie1");
        MedicalRecord medicalRecord = new MedicalRecord("firstName1"
                , "lastName1"
                , birthDate
                , medications
                , allergies);
        Mockito.when(medicalRecordService.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecord);
        Mockito.when(calculateFonction.calculateAge(any(Date.class))).thenReturn(25);
        // WHEN
        List<InfoPersonDetailedDTO> listInfoPerson = personService.getPersonByFirstLastName("firstName", "lastName");
        // THEN
        assertThat(listInfoPerson.size()).isEqualTo(3);
        assertThat(listInfoPerson.get(0).getMedications()).isEqualTo(medicalRecord.getMedications());
        assertThat(listInfoPerson.get(0).getAllergies()).isEqualTo(medicalRecord.getAllergies());
    }

    @Test
    public void getChildAlertByAddressTest() throws Exception {
        // GIVEN
        List<Person> listPersons = new ArrayList<>();
        Person person1 = new Person("firstName1", "lastName1", "adress1", "City1", "", "", "email1@test.fr");
        Person person2 = new Person("firstName2", "lastName2", "adress2", "City2", "", "", "email2@test.fr");
        Person person3 = new Person("firstName3", "lastName3", "adress3", "City2", "", "", "email3@test.fr");
        listPersons.add(person1);
        listPersons.add(person2);
        listPersons.add(person3);
        Mockito.when(personDAO.findPersonByAdress(any(String.class))).thenReturn(listPersons);

        String Date = "01/01/2000";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse(Date);
        List<String> medications = new ArrayList<String>();
        medications.add("medication1");
        List<String> allergies = new ArrayList<String>();
        allergies.add("allergie1");
        MedicalRecord medicalRecord = new MedicalRecord("firstName1"
                , "lastName1"
                , birthDate
                , medications
                , allergies);
        Mockito.when(medicalRecordService.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecord);
        Mockito.when(calculateFonction.calculateAge(any(Date.class))).thenReturn(17);
        // WHEN
        List<ChildAlertDTO> listChildAlert = personService.getChildAlertByAddress("addressToFind");
        // THEN
        assertThat(listChildAlert.size()).isEqualTo(3);
        assertThat(listChildAlert.get(0).getFirstName()).isEqualTo(person1.getFirstName());
        assertThat(listChildAlert.get(1).getFirstName()).isEqualTo(person2.getFirstName());
        assertThat(listChildAlert.get(2).getFirstName()).isEqualTo(person3.getFirstName());

    }

    @Test
    public void getPersonByAddressTest() throws Exception {
        // GIVEN
        List<Person> listPersons = new ArrayList<>();
        Person person1 = new Person("firstName1", "lastName1", "adress1", "City1", "", "", "email1@test.fr");
        Person person2 = new Person("firstName2", "lastName2", "adress2", "City2", "", "", "email2@test.fr");
        Person person3 = new Person("firstName3", "lastName3", "adress3", "City2", "", "", "email3@test.fr");
        listPersons.add(person1);
        listPersons.add(person2);
        listPersons.add(person3);
        Mockito.when(personDAO.findPersonByAdress(any(String.class))).thenReturn(listPersons);

        String Date = "01/01/2000";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse(Date);
        List<String> medications = new ArrayList<String>();
        medications.add("medication1");
        List<String> allergies = new ArrayList<String>();
        allergies.add("allergie1");
        MedicalRecord medicalRecord = new MedicalRecord("firstName1"
                , "lastName1"
                , birthDate
                , medications
                , allergies);
        Mockito.when(medicalRecordService.findMedicalRecord(any(String.class), any(String.class)))
                .thenReturn(medicalRecord);
        Mockito.when(calculateFonction.calculateAge(any(Date.class))).thenReturn(45);
        Mockito.when(fireStationDAO.getStationByAddress(any(String.class))).thenReturn("3");
        // WHEN
        List<FireDTO> listFireDTO = personService.getPersonByAddress("addressToFind");
        // THEN
        assertThat(listFireDTO.size()).isEqualTo(3);
        assertThat(listFireDTO.get(0).getFirstName()).isEqualTo(person1.getFirstName());
        assertThat(listFireDTO.get(1).getFirstName()).isEqualTo(person2.getFirstName());
        assertThat(listFireDTO.get(2).getFirstName()).isEqualTo(person3.getFirstName());

    }

}
