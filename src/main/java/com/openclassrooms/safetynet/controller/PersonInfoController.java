package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.openclassrooms.safetynet.dto.FireDTO;
import com.openclassrooms.safetynet.dto.InfoPersonDTO;
import com.openclassrooms.safetynet.dto.InfoPersonDetailedDTO;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonInfoController {
    private static final Logger logger = LogManager.getLogger("PersonInfoController");

    @Autowired
    PersonService personService;

    @GetMapping(value = "/personInfo")
    public List<InfoPersonDetailedDTO> getPersonInfo(@RequestParam("firstName") String firstName,
                                                     @RequestParam("lastName") String lastName) throws Exception {
        logger.info("getPersonInfo : OK");
        return personService.getPersonByFirstLastName(firstName, lastName);
    }
}

