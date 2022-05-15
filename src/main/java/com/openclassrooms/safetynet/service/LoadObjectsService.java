package com.openclassrooms.safetynet.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.data.ObjectsSafetyNet;
import com.openclassrooms.safetynet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
@Service
public class LoadObjectsService implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger("LoadObjectsService");

    @Value("${filename}")
    private String fileName;

    @Override
    public void run(String... args) throws Exception {

        try {
            ObjectMapper mapper = new ObjectMapper();

            ObjectsSafetyNet object = mapper.readValue(new File(fileName), ObjectsSafetyNet.class);

            List<Person> listPerson = object.getPersons();
            List<FireStation> listFireStation = object.getFirestations();
            List<MedicalRecord> listMedicalRecord = object.getMedicalrecords();

            // Alimentation du DTO
            //personDao.setAllPersons(listPerson);
            Data.setPersons(listPerson);
            //fireStationDao.setAllFireStations(listFireStation);
            Data.setFireStations(listFireStation);
            //medicalRecordsDao.setAllMedicalRecords(listmedicalRecord);
            Data.setMedicalRecords(listMedicalRecord);

            logger.info("Loaded Json objects");

        } catch (JsonMappingException e) {
            e.printStackTrace();
            logger.error("Mapping JSON Error", e);
        } catch (JsonParseException e) {
            e.printStackTrace();
            logger.error("Parsing JSON Error", e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("Input/Output JSON Error", e);
        }
    }
}
