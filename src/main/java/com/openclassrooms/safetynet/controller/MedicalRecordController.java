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

}
