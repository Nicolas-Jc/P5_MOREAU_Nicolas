package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileReader;


@Component
public class LoadFileJacksonObjectMapper implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Entree Run");

        //create ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        FileReader reader = new FileReader("src/main/resources/json/dataIn.json");
        //convert json string to object
        Dto dto = mapper.readValue(reader, Dto.class);

        System.out.println("OBJECT PERSON\n" + dto.getPersons());
        System.out.println("OBJECT FIRESTATION\n" + dto.getFirestations());
        System.out.println("OBJECT MEDICALRECORD\n" + dto.getMedicalrecords());
    }
}


