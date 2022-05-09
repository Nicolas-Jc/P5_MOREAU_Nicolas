package com.openclassrooms.safetynet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.dao.*;
import com.openclassrooms.safetynet.data.Data;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.data.ObjectsSafetyNet;
import com.openclassrooms.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@Service
public class LoadObjectsService implements CommandLineRunner {

    @Autowired
    PersonDAO personDao;

    @Autowired
    FireStationDAO fireStationDao;

    @Autowired
    MedicalRecordsDAO medicalRecordsDao;

    @Value("${filename}")
    private String fileName;


    @Override
    public void run(String... args) throws Exception {

        System.out.println("Entrée LoadObjectsService");

        //Path path = Paths.get("src\\main\\resources\\json\\dataIn.json");


        List<Person> listPerson = new ArrayList<>();
        List<FireStation> listFireStation = new ArrayList<>();
        List<MedicalRecord> listMedicalRecord = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        ObjectsSafetyNet object = mapper.readValue(new File(fileName), ObjectsSafetyNet.class);

        listPerson = object.getPersons();
        listFireStation = object.getFirestations();
        listMedicalRecord = object.getMedicalrecords();

        System.out.println("listPerson :" + listPerson);
        System.out.println("listFireStation :" + listFireStation);
        System.out.println("listmedicalRecord :" + listMedicalRecord);
        //System.out.println(listmedicalRecord.toString());

        //personDao.setAllPersons(listPerson);
        Data.setPersons(listPerson);
        //fireStationDao.setAllFireStations(listFireStation);
        Data.setFireStations(listFireStation);
        //medicalRecordsDao.setAllMedicalRecords(listmedicalRecord);
        Data.setMedicalRecords(listMedicalRecord);

    }
}
