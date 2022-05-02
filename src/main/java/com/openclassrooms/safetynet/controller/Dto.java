package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dto {

    //@JsonProperty("persons")
    private List<Person> persons;
    private List<FireStation> firestations;
    //private List<MedicalRecord> medicalrecords;
    private List<MedicalRecord> medicalrecords;


}
