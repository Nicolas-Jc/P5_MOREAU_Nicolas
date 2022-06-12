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

    public FireStationDTO fireStationPersonsScope(String station) {

        FireStationDTO fireStationDTO = new FireStationDTO();

        // Format de fichier attendu en sortie (constituée d'une Liste et 2 compteurs)
        //List<FireStationDTO> fireStationDTO = new ArrayList<>();

        // Format de fichier en sortie (Liste SANS les compteurs)
        //List<InfoPersonDTO> fireStationPersons = new ArrayList<>();

        int countChild = 0;
        int countAdult = 0;
        int agePerson = 0;

        // Liste des différentes adresses desservies par le No de station saisi dans le Json Entree
        List<String> listAdress = fireStationDAO.getFireStationAdressById(station);

        // Création Liste attendue vide pour sortie (Liste : Prenom, Nom, Adresse et No Tel + Compteurs)
        //List<FireStationDTO> listPersonInfo = new ArrayList<>();

        // Récupération de la Liste de toutes les persons du fichier en entrée
        //List<Person> listPersons = findAll();
        List<Person> listPersons = personDAO.getAllPersons();

        // Boucle sur chaque occurence de la liste persons (Fichier entrée)
        for (Person p : listPersons) {

            if (listAdress.contains(p.getAddress())) {
                MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());
                InfoPersonDTO fireStationPersons = new InfoPersonDTO();

                fireStationPersons.setFirstName(p.getFirstName());
                fireStationPersons.setLastName(p.getLastName());
                fireStationPersons.setAddress(p.getAddress());
                fireStationPersons.setPhone(p.getPhone());
                //fireStationDTO.add(fireStationPersons);

                if (medicalRecord != null) {
                    agePerson = calculateFonction.calculateAge(medicalRecord.getBirthdate());
                }
                if (agePerson <= 18) {
                    countChild++;
                    System.out.println(countChild);
                } else {
                    countAdult++;
                    System.out.println(countAdult);
                }
            }
            // Liste des personnes (Sans les compteurs)
            //fireStationPersons.add(fireStationPersons);

        }
        //fireStationDTO.add(fireStationPersons);


        // Mise en forme Liste final : Liste personnes + 2 compteurs
        /*fireStationDTO.setFireStationPersons(fireStationPersons);
        fireStationDTO.setAdultCount(countAdult);
        fireStationDTO.setChildCount(countChild);*/

        //return fireStationDTO;
        return null;

    }

    /*public String stationNumber(String address) {
        return fireStationDAO.getStationByAddress(address);
    }*/

}
