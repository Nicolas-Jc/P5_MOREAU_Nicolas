package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.service.FireStationService;
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

    /*
    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/fire")
    public List<FireDTO> getFireListPerson(@RequestParam("address") String address) throws Exception {

        logger.info("getFireListPerson OK");
        return fireStationService.getFireListPersons(address);
    }*/
}
