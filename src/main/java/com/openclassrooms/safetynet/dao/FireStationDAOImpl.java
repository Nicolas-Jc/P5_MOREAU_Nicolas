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
    // Fonction qui retourne la liste "adresses" couvertes par un numero de station donné
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

    @Override
    public List<FireStation> deleteFireStation(FireStation fireStation) {

        List<FireStation> fireStationsDeleted = new ArrayList<>();

        // delete with address
        if (fireStation.getStation().isEmpty()) {
            for (FireStation f : Data.getFireStations()) {
                if (f.getAddress().contentEquals(fireStation.getAddress())) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        // delete with station
        if (fireStation.getAddress().isEmpty()) {
            for (FireStation f : Data.getFireStations()) {
                if (f.getStation().contentEquals(fireStation.getStation())) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        // delete with station and address
        if (!fireStation.getAddress().isEmpty() && !fireStation.getStation().isEmpty()) {
            for (FireStation f : Data.getFireStations()) {
                if ((f.getStation().contentEquals(fireStation.getStation()))
                        && (f.getAddress().contentEquals(fireStation.getAddress()))) {
                    fireStationsDeleted.add(f);
                }
            }
        }

        if (fireStationsDeleted.size() != 0)
            Data.getFireStations().removeAll(fireStationsDeleted);
        return fireStationsDeleted;
    }

    @Override
    public FireStation modifyFireStation(FireStation fireStation) {
        int key = 0;

        for (FireStation f : Data.getFireStations()) {
            if (f.getAddress().contentEquals(fireStation.getAddress())) {
                Data.getFireStations().set(key, fireStation);
                return fireStation;
            }
            key++;
        }
        return null;
    }

    @Override
    public FireStation addFireStation(FireStation fireStation) {
        Data.getFireStations().add(fireStation);
        return fireStation;
    }

    @Override
    public String getStationByAddress(String address) {
        for (FireStation f : Data.getFireStations()) {
            if (f.getAddress().contentEquals(address)) {
                return f.getStation();
            }
        }
        return "Not Found";
    }


}
