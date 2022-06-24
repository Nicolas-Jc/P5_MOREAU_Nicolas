package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/medicalRecord")
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger("MedicalRecordController");

    @Autowired
    MedicalRecordServiceImpl medicalRecordService;

    @DeleteMapping
    public String deleteMedicalRecord(@RequestParam("firstName") String firstName,
                                      @RequestParam("lastName") String lastName) {

        String medicalRecordName = firstName + " " + lastName;

        if (medicalRecordService.delete(firstName, lastName)) {
            logger.info("Deleteted medicalrecord : " + medicalRecordName);
            return "Deleted medicalrecord : " + medicalRecordName;
        }
        return null;
    }

    @PutMapping
    public MedicalRecord modifyMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        MedicalRecord medicalRecordModified = medicalRecordService.modify(medicalRecord);

        if (medicalRecordModified == null) {
            logger.error("modifyMedicalRecord KO");
        } else {
            logger.info("modified medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        }
        return medicalRecordModified;
    }

    @PostMapping
    public MedicalRecord addMedicalRecord(@RequestBody MedicalRecord medicalRecord) {

        MedicalRecord medicalRecordAdded = medicalRecordService.add(medicalRecord);

        if (medicalRecordAdded == null) {
            logger.error("addMedicalRecord KO");
        } else {
            logger.info("created medicalrecord : " + medicalRecord.getFirstName() + " " + medicalRecord.getLastName());
        }
        return medicalRecordAdded;

    }

}
