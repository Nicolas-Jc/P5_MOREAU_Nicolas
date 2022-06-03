package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.CommunityEmailDTO;
import com.openclassrooms.safetynet.dto.PhoneAlertDTO;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    public List<Person> getPerson() {
        return personService.findAll();
    }

    // ********************* EndPoint No 1 - communityEmail ***************************************
    @GetMapping(value = "/communityEmail")
    public List<CommunityEmailDTO> getCommunityEmail(@RequestParam("city") String city) throws Exception {

        if (city.isEmpty()) {
            logger.error("getCommunityEmail => city empty !");
            throw new Exception("city is empty");
        }
        logger.info("getCommunityEmail : OK");
        return personService.getCommunityEmailByCity(city);

    }

    // ********************* EndPoint No 2 - phoneAlert ***************************************
    @GetMapping(value = "/phoneAlert")
    public List<PhoneAlertDTO> getPhoneAlert(@RequestParam("firestation") String station) throws Exception {

        logger.info("getPhoneAlert : OK");
        return personService.getPhoneAlertByFirestation(station);
    }

    /*
    // ********************* EndPoint No 3 - childAlert ***************************************
    @GetMapping(value = "/childAlert")
    public List<ChildAlertDTO> getChildAlert(@RequestParam("address") String address) throws Exception {

        logger.info("getChildAlert : OK");
        return personService.getChildAlertByAddress(address);
    }*/
}
