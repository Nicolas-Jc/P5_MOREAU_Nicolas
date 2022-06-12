package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.PhoneAlertDTO;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AlertController {

    private static final Logger logger = LogManager.getLogger("AlertController");

    @Autowired
    PersonService personService;

    // ********************* EndPoint No 2 - phoneAlert ***************************************
    @GetMapping(value = "/phoneAlert")
    public List<PhoneAlertDTO> getPhoneAlert(@RequestParam("firestation") String station) throws Exception {

        logger.info("getPhoneAlert : OK");
        return personService.getPhoneAlertByFirestation(station);
    }

    // ********************* EndPoint No 3 - childAlert ***************************************
    @GetMapping(value = "/childAlert")
    public List<ChildAlertDTO> getChildAlert(@RequestParam("address") String address) throws Exception {

        logger.info("getChildAlert : OK");
        return personService.getChildAlertByAddress(address);
    }
}
