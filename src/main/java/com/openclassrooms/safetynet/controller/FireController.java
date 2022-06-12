package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.service.FireStationService;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
public class FireController {

    private static final Logger logger = LogManager.getLogger("FireController");

    @Autowired
    FireStationService fireStationService;

    @Autowired
    PersonService personService;

    // ********************* EndPoint No 4 - fire ***************************************
    @GetMapping(value = "/fire")
    public List<FireDTO> getFire(@RequestParam("address") String address) throws Exception {

        logger.info("getFire : OK");
        return personService.getPersonByAddress(address);
    }

}
