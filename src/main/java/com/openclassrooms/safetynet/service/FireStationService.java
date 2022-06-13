package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class FireStationService {

    @Autowired
    private FireStationDAO fireStationDAO;

    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private CalculateFonction calculateFonction;


    public List<FireStation> findAll() {
        return fireStationDAO.getAllFireStations();
    }


    public List<FireStation> delete(FireStation fireStation) {
        return fireStationDAO.deleteFireStation(fireStation);
    }

    public FireStation modify(FireStation fireStation) {
        return fireStationDAO.modifyFireStation(fireStation);

    }

    public FireStation add(FireStation fireStation) {
        return fireStationDAO.addFireStation(fireStation);
    }

    public FireStationPerimeter fireStationPersonsScope(String station) {

        // Liste Attendue en sortie avec les 2 compteurs
        FireStationPerimeter fireStationPerimeter = new FireStationPerimeter();

        // Liste attendue en sortie SANS les compteurs
        List<PersonFireStationDTO> personFireStationDTO = new ArrayList<>();

        int countChild = 0;
        int countAdult = 0;
        int agePerson = 0;

        // Liste des différentes adresses desservies par le No de station saisi dans le Json Entree
        List<String> listAdress = fireStationDAO.getFireStationAdressById(station);

        // Récupération de la Liste de toutes les persons du fichier en entrée
        List<Person> listPersonsAll = personDAO.getAllPersons();

        // Boucle sur chaque occurence de la liste persons (Fichier entrée)
        for (Person p : listPersonsAll) {

            if (listAdress.contains(p.getAddress())) {
                MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
                PersonFireStationDTO fireStationPersons = new PersonFireStationDTO();

                fireStationPersons.setFirstName(p.getFirstName());
                fireStationPersons.setLastName(p.getLastName());
                fireStationPersons.setAddress(p.getAddress());
                fireStationPersons.setPhone(p.getPhone());

                if (medicalRecord != null) {
                    agePerson = calculateFonction.calculateAge(medicalRecord.getBirthdate());
                }
                if (agePerson <= 18) {
                    countChild++;
                } else {
                    countAdult++;
                }
                personFireStationDTO.add(fireStationPersons);
            }
        }

        fireStationPerimeter.setFireStationPersons(personFireStationDTO);
        fireStationPerimeter.setAdultCount(countAdult);
        fireStationPerimeter.setChildCount(countChild);

        return fireStationPerimeter;

    }


    public List<FloodDTO> getFloodListByStationsList(List<String> stations) {

        int agePerson = 0;

        // Format fichier attendu en sortie
        List<FloodDTO> result = new ArrayList<>();

        // Liste des addresses à compléter
        List<String> adresses = new ArrayList<>();

        // 1. Boucle sur chacune des No de stations de la Liste
        // et récupération des adresses associés pour chacun
        for (String s : stations) {
            List<String> adressesForStation = new ArrayList<>();
            adressesForStation = fireStationDAO.getFireStationAdressById(s);
            adresses.addAll(adressesForStation);
        }

        // 2. Boucle sur chacune des adresses et recherche des foyers concernés par adresse
        for (String a : adresses) {
            List<Person> allPersonInAdress = personDAO.findPersonByAdress(a);

            FloodDTO floodDTO = new FloodDTO();
            floodDTO.setAddress(a);

            // 3. Pour chacune des adresses, générérer la Liste des foyers concernés
            // Liste de travail intermédiaire
            List<FloodPersonDTO> listFloodPerson = new ArrayList<>();

            for (Person p : allPersonInAdress) {

                MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
                FloodPersonDTO floodPerson = new FloodPersonDTO();
                floodPerson.setFirstName(p.getFirstName());
                floodPerson.setLastName(p.getLastName());
                floodPerson.setPhone(p.getPhone());

                if (medicalRecord != null) {
                    agePerson = calculateFonction.calculateAge(medicalRecord.getBirthdate());
                    floodPerson.setAge(agePerson);
                    floodPerson.setMedications(medicalRecord.getMedications());
                    floodPerson.setAllergies(medicalRecord.getAllergies());
                }
                listFloodPerson.add(floodPerson);
            }

            floodDTO.setFloodListPersons(listFloodPerson);
            result.add(floodDTO);
        }
        return result;
    }
}
