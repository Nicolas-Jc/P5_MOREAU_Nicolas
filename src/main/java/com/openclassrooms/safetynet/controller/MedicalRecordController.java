package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger("MedicalRecordController");

    @Autowired
    MedicalRecordService medicalRecordService;

    /*@GetMapping("/medicalrecords")
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordService.findAll();
    }*/

    @DeleteMapping
    public String deleteMedicalRecord(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName) {

        String medicalRecordName = firstName + " " + lastName;

        if (medicalRecordService.delete(firstName, lastName)) {
            logger.info("Deleteted medicalrecord : " + firstName + " " + lastName);
            return "Deleteted medicalrecord : " + firstName + " " + lastName;
        } /*else {
            logger.error("removeMedicalRecord KO");
            throw new MedicalRecordNotFound(medicalRecordName);
        }*/
        return null;
    }

    @PutMapping
    public MedicalRecord modifyMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        MedicalRecord medicalRecordModified = medicalRecordService.modify(medicalRecord);

        if (medicalRecordModified == null) {
            logger.error("modifyMedicalRecord KO");
            //throw new MedicalRecordNotFound(medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        }
        logger.info("modified medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        return medicalRecordModified;

    }

    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        MedicalRecord medicalRecordAdded = medicalRecordService.add(medicalRecord);
        if (medicalRecordAdded == null) {
            logger.error("addMedicalRecord KO");
            //throw new MedicalRecordPersonNotFound(medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        }
        logger.info("created medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        return medicalRecordAdded;

    }

}
