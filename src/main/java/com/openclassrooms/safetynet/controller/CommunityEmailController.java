package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.dto.CommunityEmailDTO;
import com.openclassrooms.safetynet.service.PersonServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommunityEmailController {

    private static final Logger logger = LogManager.getLogger("CommunityEmailController");
    @Autowired
    PersonServiceImpl personService;

    @GetMapping(value = "/communityEmail")
    public List<CommunityEmailDTO> getCommunityEmail(@RequestParam("city") String city) throws Exception {

        if (city.isEmpty()) {
            logger.error("getCommunityEmail => city empty !");
            throw new Exception("city is empty");
        }
        logger.info("getCommunityEmail : OK");
        return personService.getCommunityEmailByCity(city);

    }
}
