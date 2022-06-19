package com.openclassrooms.safetynet.DAO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.openclassrooms.safetynet.dao.MedicalRecordsDAO;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@SpringBootTest
public class MedicalRecordDAOTest {

    @Autowired
    MedicalRecordsDAO medicalRecordDAO;

    @Test
    public void getMedicalRecordExistingTest() throws ParseException {
        // GIVEN
        // WHEN
        MedicalRecord medicalRecord = medicalRecordDAO.getMedicalRecords("FirstNameGet", "LastNameGet");
        // THEN ---
        assertThat(medicalRecord.getBirthdate()).isNotNull();
        assertThat(medicalRecord.getMedications().get(0).contains("TestGethydrapermazol"));
        assertThat(medicalRecord.getAllergies().get(0).contains("TestGetshellfish"));
    }

    @Test
    public void getMedicalRecordNotExistingTest() {
        // GIVEN // WHEN // THEN
        assertThat(medicalRecordDAO.getMedicalRecords("FirstNameNotExistingTest", "LastNameNotExistingTest")).isNull();
    }

    @Test
    public void addMedicalRecordTest() throws ParseException {
        // GIVEN
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse("01/01/2022");
        List<String> medications = new ArrayList<String>();
        medications.add("TestAddMedication");
        List<String> allergies = new ArrayList<String>();
        allergies.add("TestAddAllergie");
        MedicalRecord medicalRecordTest = new MedicalRecord("FirstNameAdd"
                , "LastNameAdd"
                , birthDate
                , medications,
                allergies);
        // WHEN
        MedicalRecord medicalRecordAdded = medicalRecordDAO.addMedicalRecords(medicalRecordTest);
        // THEN
        assertThat(medicalRecordAdded.getFirstName()).isEqualTo(medicalRecordTest.getFirstName());
        assertThat(medicalRecordAdded.getLastName()).isEqualTo(medicalRecordTest.getLastName());
        assertThat(medicalRecordAdded.getBirthdate()).isEqualTo(medicalRecordTest.getBirthdate());
        assertThat(medicalRecordAdded.getMedications()).isEqualTo(medicalRecordTest.getMedications());
        assertThat(medicalRecordAdded.getAllergies()).isEqualTo(medicalRecordTest.getAllergies());
    }

    @Test
    public void modifyMedicalRecordExistingTest() throws ParseException {
        // GIVEN
        String Date = "15/15/2015";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDateToModify = dateFormat.parse(Date);
        List<String> medications = new ArrayList<String>();
        medications.add("TestModifyAznol_NoUpdate");
        medications.add("TestModifyPharmacol_IS_UPDATED");
        List<String> allergies = new ArrayList<String>();
        allergies.add("TestModifyShellfish_IS_UPDATED");
        allergies.add("TestModifyPeanut_NoUpdate");

        MedicalRecord medicalRecordModify = new MedicalRecord("FirstNameModify"
                , "LastNameModify"
                , birthDateToModify
                , medications
                , allergies);
        // WHEN
        MedicalRecord medicalRecordUpdated = medicalRecordDAO.modifyMedicalRecords(medicalRecordModify);
        // THEN
        assertThat(medicalRecordUpdated.getBirthdate()).isEqualTo(medicalRecordModify.getBirthdate());
        assertThat(medicalRecordUpdated.getMedications().get(1)).isEqualTo(medicalRecordModify.getMedications().get(1));
        assertThat(medicalRecordUpdated.getAllergies().get(0)).isEqualTo(medicalRecordModify.getAllergies().get(0));
    }

    @Test
    public void modifyMedicalRecordNotExistingTest() throws ParseException {
        // GIVEN
        String stringDate = "99/99/1999";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date birthDate = dateFormat.parse(stringDate);
        List<String> medications = new ArrayList<String>();
        medications.add("");
        List<String> allergies = new ArrayList<String>();
        allergies.add("");

        MedicalRecord medicalRecordTest = new MedicalRecord("FirstnameNotExist"
                , "LastnameNotExist"
                , birthDate
                , medications
                , allergies);
        // WHEN // THEN
        assertThat(medicalRecordDAO.modifyMedicalRecords(medicalRecordTest)).isNull();
    }

    @Test
    public void deleteExistingMedicalRecordTest() {
        // GIVEN // WHEN // THEN
        assertThat(medicalRecordDAO.deleteMedicalRecords("firstnameToDeleteTest"
                , "lastnameToDeleteTest")).isTrue();
    }

    @Test
    public void deleteNotExistingMedicalRecordTest() {

        // GIVEN // WHEN // THEN
        assertThat(medicalRecordDAO.deleteMedicalRecords("firstnameToDeleteNoExist"
                , "lastnameToDeleteNoExist")).isFalse();
    }
}
