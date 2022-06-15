package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireStationPerimeterDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationServiceImpl;
import com.openclassrooms.safetynet.service.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger logger = LogManager.getLogger("FireStationController");

    @Autowired
    FireStationServiceImpl fireStationService;

    @Autowired
    PersonServiceImpl personService;

    @GetMapping
    public FireStationPerimeterDTO getFireStationCoverage(@RequestParam("stationNumber") String station) throws Exception {

        if (station.isEmpty()) {
            logger.error("getFireStationCoverage => station empty !");
            throw new Exception("station is empty");
        }
        logger.info("getFireStationCoverage OK");
        return fireStationService.fireStationPersonsScope(station);
    }

    @DeleteMapping
    public List<FireStation> deleteFireStation(@RequestBody FireStation fireStation) {

        List<FireStation> fireStationDeleted = fireStationService.delete(fireStation);

        if (fireStationDeleted.size() != 0) {
            logger.info("Deleted firestation : " + fireStationDeleted.toString());
            return fireStationDeleted;
        } else {
            logger.error("deleteFireStation : KO");
            return null;
        }
    }

    @PutMapping
    public FireStation modifyFireStation(@RequestBody FireStation fireStation) {

        FireStation fireStationModified = fireStationService.modify(fireStation);

        if (fireStationModified != null) {
            logger.info("Modified firestation : " + fireStationModified.toString());
            return fireStationModified;
        } else {
            logger.error("modifyFireStation : KO");
            return null;
        }
    }

    @PostMapping
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        logger.info("Add firestation : " + fireStation.toString());

        return fireStationService.add(fireStation);

    }

}
