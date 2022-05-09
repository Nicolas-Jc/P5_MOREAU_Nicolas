package com.openclassrooms.safetynet.data;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static List<Person> persons = new ArrayList<>();
    private static List<FireStation> fireStations = new ArrayList<>();
    private static List<MedicalRecord> medicalRecords = new ArrayList<>();

    public static List<Person> getPersons() {
        return persons;
    }

    public static void setPersons(List<Person> persons) {
        Data.persons = persons;
    }

    public static List<FireStation> getFireStations() {
        return fireStations;
    }

    public static void setFireStations(List<FireStation> fireStations) {
        Data.fireStations = fireStations;
    }

    public static List<MedicalRecord> getMedicalRecords() {
        return medicalRecords;
    }

    public static void setMedicalRecords(List<MedicalRecord> medicalRecords) {
        Data.medicalRecords = medicalRecords;
    }
}
