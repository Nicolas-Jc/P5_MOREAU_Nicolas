package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.FireStationServiceImpl;
import com.openclassrooms.safetynet.service.MedicalRecordServiceImpl;
import com.openclassrooms.safetynet.service.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AllObjectsController {
    private static final Logger logger = LogManager.getLogger("AllObjectsController");

    @Autowired
    PersonServiceImpl personService;

    @Autowired
    MedicalRecordServiceImpl medicalRecordService;

    @Autowired
    FireStationServiceImpl fireStationService;

    @GetMapping("/persons")
    public List<Person> getPerson() {
        logger.info("getPerson : OK");
        return personService.findAll();
    }

    @GetMapping("/medicalrecords")
    public List<MedicalRecord> getMedicalRecords() {
        logger.info("getMedicalRecords : OK");
        return medicalRecordService.findAll();
    }

    @GetMapping("/firestations")
    public List<FireStation> getFireStations() {
        logger.info("getFireStations : OK");
        return fireStationService.findAll();
    }

}
