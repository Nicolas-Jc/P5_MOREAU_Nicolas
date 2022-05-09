package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.model.FireStation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

;

@Service
public class FireStationService {

    @Autowired
    private FireStationDAO fireStationDAO;

    // @Autowired
    //private PersonService personService;

    //@Autowired
    //private MedicalRecordService medicalRecordService;

    //@Override
    public List<FireStation> findAll() {
        return fireStationDAO.getAllFireStations();
    }

}
