package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.FireStationPerimeter;
import com.openclassrooms.safetynet.dto.PersonFireStationDTO;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/firestation")
public class FireStationController {

    private static final Logger logger = LogManager.getLogger("FireStationController");

    @Autowired
    FireStationService fireStationService;

    @Autowired
    PersonService personService;

    @GetMapping
    public FireStationPerimeter getFireStationCoverage(@RequestParam("stationNumber") String station) throws Exception {
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
            logger.error("removeFireStation : KO");
            //throw new FireStationNotFound(fireStation.toString());
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
            logger.error("Modified firestation : KO");
            //throw new FireStationNotFound(fireStation.toString());
            return null;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FireStation addFireStation(@RequestBody FireStation fireStation) {
        logger.info("Add firestation : " + fireStation.toString());

        return fireStationService.add(fireStation);

    }

}
