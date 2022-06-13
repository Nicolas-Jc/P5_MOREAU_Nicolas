package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.dto.FloodDTO;
import com.openclassrooms.safetynet.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
            logger.error("getFloodList : stations are empty");
            throw new Exception("list of stations is empty");
        }
        logger.info("getFloodList OK");
        return fireStationService.getFloodListByStationsList(stations);

    }

}
