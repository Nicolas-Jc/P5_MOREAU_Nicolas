package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.model.FireStation;

import java.util.List;

public interface FireStationDAO {

    public List<FireStation> getAllFireStations();

    public void setAllFireStations(List<FireStation> listFireStations);

    List<String> getFireStationAdressById(String station);

    List<FireStation> deleteFireStation(FireStation fireStation);

    FireStation modifyFireStation(FireStation fireStation);

    FireStation addFireStation(FireStation fireStation);
}
