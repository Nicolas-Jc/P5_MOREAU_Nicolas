package com.openclassrooms.safetynet.DAO;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.model.FireStation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class FireStationDAOTest {

    @Autowired
    FireStationDAO fireStationDAO;

    /*@Test
    public void getAllFireStationsTest() {
        // GIVEN
        List<FireStation> fireStationGetAll = fireStationDAO.getAllFireStations();
        // WHEN
        // THEN
        assertThat(fireStationGetAll.size()).isEqualTo(6);
    }*/

    @Test
    public void getStationByAddressExistingTest() {
        //GIVEN
        // WHEN
        // THEN
        assertThat(fireStationDAO.getStationByAddress("99 Street Test FindPersonByAdress"))
                .isEqualTo("1");
    }

    @Test
    public void getStationByAddressNotExistingTest() {
        assertThat(fireStationDAO.getStationByAddress("adresstestXX")).isEqualTo("Not Existing Address");
    }

    @Test
    public void getFireStationAdressByIdTest() {
        // GIVEN // WHEN // THEN
        assertThat(fireStationDAO.getFireStationAdressById("1")).isNotNull();
    }

    @Test
    public void getFireStationAdressByIdNotExistingTest() {
        // GIVEN // WHEN // THEN
        assertThat(fireStationDAO.getFireStationAdressById("99")).isEmpty();
    }

    @Test
    public void addFireStationTest() {
        // GIVEN
        FireStation fireStationTest = new FireStation("AdressToAdd", "stationToAdd");
        // WHEN
        FireStation fireStationAdded = fireStationDAO.addFireStation(fireStationTest);
        // THEN
        assertThat(fireStationAdded.getAddress()).isEqualTo(fireStationTest.getAddress());
        assertThat(fireStationAdded.getStation()).isEqualTo(fireStationTest.getStation());

    }

    @Test
    public void modifyFireStationTest() {
        // GIVEN
        FireStation fireStationTest = new FireStation("adressStationToModify", "9");
        // WHEN
        FireStation fireStationModified = fireStationDAO.modifyFireStation(fireStationTest);
        // THEN
        assertThat(fireStationModified.getAddress()).isEqualTo(fireStationTest.getAddress());
        assertThat(fireStationModified.getStation()).isEqualTo(fireStationTest.getStation());
    }

    @Test
    public void deleteFireStationByAdressTest() {
        // GIVEN
        FireStation fireStationToDelete = new FireStation("adressToDelete", "");
        // WHEN
        // THEN
        assertThat(fireStationDAO.deleteFireStation(fireStationToDelete)).isNotNull();
    }

    @Test
    public void deleteFireStationByStationTest() {
        // GIVEN
        FireStation fireStationToDelete = new FireStation("", "DDDDD");
        // WHEN
        // THEN
        assertThat(fireStationDAO.deleteFireStation(fireStationToDelete)).isNotNull();
    }

    @Test
    public void deleteFireStationByAdressAndStationTest() {
        // GIVEN
        FireStation fireStationToDelete = new FireStation("XXXXXX", "XXXXXX");
        // WHEN
        // THEN
        assertThat(fireStationDAO.deleteFireStation(fireStationToDelete)).isNotNull();
    }

}
