package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FireStationDAOImpl implements FireStationDAO {
    //private List<FireStation> fireStations = new ArrayList<>();

    @Override
    public List<FireStation> getAllFireStations() {
        //return fireStations;
        return Data.getFireStations();
    }

    @Override
    public void setAllFireStations(List<FireStation> listFireStations) {
        //this.fireStations = listFireStations;
        Data.setFireStations(listFireStations);

    }

}
