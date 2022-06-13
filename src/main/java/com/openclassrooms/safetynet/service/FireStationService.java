package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dto.FireStationPerimeter;
import com.openclassrooms.safetynet.dto.FloodDTO;
import com.openclassrooms.safetynet.model.FireStation;

import java.util.List;

public interface FireStationService {
    List<FireStation> findAll();

    List<FireStation> delete(FireStation fireStation);

    FireStation modify(FireStation fireStation);

    FireStation add(FireStation fireStation);

    FireStationPerimeter fireStationPersonsScope(String station);

    List<FloodDTO> getFloodListByStationsList(List<String> stations);
}
