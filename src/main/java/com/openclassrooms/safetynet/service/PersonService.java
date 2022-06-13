package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.dao.MedicalRecordsDAO;
import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private FireStationDAO fireStationDAO;

    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;

    @Autowired
    private MedicalRecordService medicalRecordService;

    @Autowired
    private FireStationService fireStationService;

    @Autowired
    private CalculateFonction calculateFonction;


    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }

    public List<Person> findPerson(String firstName, String lastName) {
        return personDAO.findPersonByFirstNameAndLastName(firstName, lastName);
    }

    /*
    public List<Person> findPersonByAddress(String address) {
        return personDAO.findPersonByAdress(address);
    }*/

    // 1. URL "CommunityEmail" - EN
    // Cette url doit retourner une liste des adresses mail de tous les habitants de la ville saisie.
    public List<CommunityEmailDTO> getCommunityEmailByCity(String city) {
        // Création Liste vide pour sortie
        List<CommunityEmailDTO> listEmailInfo = new ArrayList<>();
        // Récupération de la Liste de toutes les persons du fichier en entrée
        //List<Person> listPersons = findAll();
        List<Person> listPersons = personDAO.getAllPersons();

        // Boucle sur chaque occurence de la liste persons (Fichier entrée)
        for (Person p : listPersons) {
            if (p.getCity().contentEquals(city)) {
                // mise en forme Json sortie
                CommunityEmailDTO communityEmailDTO = new CommunityEmailDTO();
                // Alimentation du DTO (Liste) avec valeur lue (adresse) sur l'occurence persons du fichier entree
                communityEmailDTO.setEmail(p.getEmail());
                // Enrichissement & Generation de la Liste en sortie et envoi au controller
                listEmailInfo.add(communityEmailDTO);
            }
        }
        return listEmailInfo;
    }

    // 2. URL "PhoneAlert"
    // Cette url doit retourner au controller une liste des numéros de téléphone des résidents desservis
    // par le numero de caserne de pompiers saisi.
    // Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
    public List<PhoneAlertDTO> getPhoneAlertByFirestation(String station) {

        // Liste des différentes adresses desservies par le No de station dans le Json Entree
        List<String> listAdress = fireStationDAO.getFireStationAdressById(station);

        // Création Liste attendue vide pour sortie
        List<PhoneAlertDTO> listPhoneInfo = new ArrayList<>();
        // Récupération de la Liste de toutes les persons du fichier en entrée
        //List<Person> listPersons = findAll();
        List<Person> listPersons = personDAO.getAllPersons();

        // Boucle sur chaque occurence de la liste persons (Fichier entrée)
        for (Person p : listPersons) {
            if (listAdress.contains(p.getAddress())) {
                // mise en forme Json sortie
                PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
                phoneAlertDTO.setPhone(p.getPhone());
                listPhoneInfo.add(phoneAlertDTO);
            }
        }
        return listPhoneInfo;

    }

    // URL No.3 "ChildAlert"
    public List<ChildAlertDTO> getChildAlertByAddress(String address) {

        List<Person> allPersonInAdress = personDAO.findPersonByAdress(address);

        // Création Liste attendue pour sortie
        List<ChildAlertDTO> listChildInfo = new ArrayList<>();

        for (Person p : allPersonInAdress) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());

            if (medicalRecord != null) {
                int age = calculateFonction.calculateAge(medicalRecord.getBirthdate());

                // Test s'il s'agit d'un enfant et génération liste de sortie avec membres famille
                if (age <= 18) {
                    ChildAlertDTO childInfo = new ChildAlertDTO();
                    childInfo.setFirstName(p.getFirstName());
                    childInfo.setLastName(p.getLastName());
                    childInfo.setAge(age);
                    childInfo.setHouseholdMembers(allPersonInAdress);
                    listChildInfo.add(childInfo);
                }
            }
        }
        return listChildInfo;
    }

    // URL No.4 "Fire"
    public List<FireDTO> getPersonByAddress(String address) {

        List<Person> allPersonInAdress = personDAO.findPersonByAdress(address);

        // Création Liste attendue pour sortie
        List<FireDTO> listPersonInfo = new ArrayList<>();

        for (Person p : allPersonInAdress) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());

            if (medicalRecord != null) {
                int age = calculateFonction.calculateAge(medicalRecord.getBirthdate());

                // Test s'il s'agit d'un enfant et génération liste de sortie avec membres famille
                //if (age <= 18) {
                FireDTO personInfo = new FireDTO();
                personInfo.setFirstName(p.getFirstName());
                personInfo.setLastName(p.getLastName());
                personInfo.setPhone(p.getPhone());
                personInfo.setAge(age);
                personInfo.setPhone(p.getPhone());
                personInfo.setStation(fireStationDAO.getStationByAddress(p.getAddress()));
                personInfo.setMedications(medicalRecord.getMedications());
                personInfo.setAllergies(medicalRecord.getAllergies());
                listPersonInfo.add(personInfo);
                //}
            }
        }
        return listPersonInfo;
    }

    public List<InfoPersonDetailedDTO> getPersonByFirstLastName(String firstName, String lastName) {

        List<InfoPersonDetailedDTO> InfoPersonDetailed = new ArrayList<>();

        //List<Person> persons = findPerson(firstName, lastName);

        List<Person> persons = personDAO.findPersonByFirstNameAndLastName(firstName, lastName);

        for (Person p : persons) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());

            if (medicalRecord != null) {
                int age = calculateFonction.calculateAge(medicalRecord.getBirthdate());

                InfoPersonDetailedDTO infoPerson = new InfoPersonDetailedDTO();
                infoPerson.setFirstName(p.getFirstName());
                infoPerson.setLastName(p.getLastName());
                infoPerson.setAddress(p.getAddress());
                infoPerson.setAge(age);
                infoPerson.setEmail(p.getEmail());
                infoPerson.setMedications(medicalRecord.getMedications());
                infoPerson.setAllergies(medicalRecord.getAllergies());
                InfoPersonDetailed.add(infoPerson);
            }
        }
        return InfoPersonDetailed;
    }

    /*public List<InfoPersonDetailedDTO> getPersonByFirstLastName(String firstName, String lastName) {

        List<Person> allPersonInAdress = personDAO.findPersonByAdress(address);
        List<Person> allPersonInFirstNameLastName = personDAO.

                // Création Liste attendue pour sortie
                List < InfoPersonDetailedDTO > listPersonDetailedInfo = new ArrayList<>();

        for (Person p : allPersonInAdress) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());

            if (medicalRecord != null) {
                int age = calculateFonction.calculateAge(medicalRecord.getBirthdate());

                // Test s'il s'agit d'un enfant et génération liste de sortie avec membres famille
                //if (age <= 18) {
                FireDTO personInfo = new FireDTO();
                personInfo.setFirstName(p.getFirstName());
                personInfo.setLastName(p.getLastName());
                personInfo.setPhone(p.getPhone());
                personInfo.setAge(age);
                personInfo.setPhone(p.getPhone());
                personInfo.setStation(fireStationDAO.getStationByAddress(p.getAddress()));
                personInfo.setMedications(medicalRecord.getMedications());
                personInfo.setAllergies(medicalRecord.getAllergies());
                listPersonDetailedInfo.add(personInfo);
                //}
            }
        }
        return listPersonDetailedInfo;
    }*/

    public Boolean delete(String firstName, String lastName) {
        return personDAO.deletePerson(firstName, lastName);
    }

    public Person add(Person person) {
        return personDAO.addPerson(person);
    }

    public Person modify(Person person) {
        return personDAO.modifyPerson(person);
    }

      /*
    public List<FireDTO> getTotalInformationPerson(Person person) {

        InfoPersonFull result = new InfoPersonFull();
        result.setFirstName(person.getFirstName());
        result.setLastName(person.getLastName());
        result.setAddress(person.getAddress());
        result.setPhone(person.getPhone());
        result.setEmail(person.getEmail());
        result.setStation(fireStationService.stationNumber(person.getAddress()));

        MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(person.getFirstName(),
                person.getLastName());
        if (medicalRecord != null) {
            result.setAge(serviceUtil.calculateAge(medicalRecord.getBirthdate()));
            result.setAllergies(medicalRecord.getAllergies());
            result.setMedications(medicalRecord.getMedications());
        }

        return result;
    }*/
}
