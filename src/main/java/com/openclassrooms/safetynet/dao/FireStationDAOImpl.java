package com.openclassrooms.safetynet.dao;

import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.FireStation;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    @Override
    // Fonction qui retourne la liste "adresses" des adresses couvertes par un numero de station donné
    public List<String> getFireStationAdressById(String station) {
        List<String> listAddress = new ArrayList<>();
        // Lecture de la liste des FireStation du fichier d'entre Json
        for (FireStation f : Data.getFireStations()) {
            if (f.getStation().equals(station)) {
                listAddress.add(f.getAddress());
            }
        }
        return listAddress;
    }

}
