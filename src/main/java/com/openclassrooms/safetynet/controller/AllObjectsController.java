package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.PersonService;
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
    PersonService personService;

    @Autowired
    MedicalRecordService medicalRecordService;

    @Autowired
    FireStationService fireStationService;

    @GetMapping("/persons")
    public List<Person> getPerson() {
        return personService.findAll();
    }

    @GetMapping("/medicalrecords")
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.findAll();
    }

    @GetMapping("/firestations")
    public List<FireStation> getFireStations() {
        return fireStationService.findAll();
    }

}
