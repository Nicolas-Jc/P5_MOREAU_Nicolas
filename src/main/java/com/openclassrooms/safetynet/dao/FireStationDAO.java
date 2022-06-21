package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.model.FireStation;

import java.util.List;

public interface FireStationDAO {

    List<FireStation> getAllFireStations();

    void setAllFireStations(List<FireStation> listFireStations);

    List<String> getFireStationAdressByStation(String station);

    List<FireStation> deleteFireStation(FireStation fireStation);

    FireStation modifyFireStation(FireStation fireStation);

    FireStation addFireStation(FireStation fireStation);

    String getStationByAddress(String address);
}
