package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    //@Autowired
    //private MedicalRecordService medicalRecordService;

    //@Autowired
    //private FireStationService fireStationService;


    //@Override
    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }


}
