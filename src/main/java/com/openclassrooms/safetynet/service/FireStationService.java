package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FireStationPerimeterDTO;
import com.openclassrooms.safetynet.dto.FloodDTO;
import com.openclassrooms.safetynet.model.FireStation;

import java.util.List;

public interface FireStationService {
    List<FireStation> findAll();

    List<FireStation> delete(FireStation fireStation);

    FireStation modify(FireStation fireStation);

    FireStation add(FireStation fireStation);

    FireStationPerimeterDTO getFireStationPersonsScope(String station);

    List<FloodDTO> getFloodListByStationsList(List<String> stations);
}
