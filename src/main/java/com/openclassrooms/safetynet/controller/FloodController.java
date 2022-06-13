package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.openclassrooms.safetynet.dto.FloodDTO;
import com.openclassrooms.safetynet.dto.FloodPersonDTO;
import com.openclassrooms.safetynet.dto.InfoPersonDetailedDTO;
import com.openclassrooms.safetynet.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FloodController {
    private static final Logger logger = LogManager.getLogger("FloodController");

    @Autowired
    FireStationService fireStationService;

    @GetMapping(value = "/flood")
    public List<FloodDTO> getFloodList(@RequestParam("stations") List<String> stations) throws Exception {

        if (stations.isEmpty()) {
            logger.error("getFloodList : parameter is empty");
            throw new Exception("list of stations is empty");
        }
        logger.info("getFloodList OK");
        return fireStationService.getFloodListByStationsList(stations);

    }

}
