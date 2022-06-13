package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.dao.MedicalRecordsDAO;
import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.*;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private FireStationDAO fireStationDAO;

    @Autowired
    private MedicalRecordsDAO medicalRecordsDAO;

    @Autowired
    private MedicalRecordServiceImpl medicalRecordService;

    @Autowired
    private FireStationServiceImpl fireStationService;

    @Autowired
    private CalculateFonction calculateFonction;


    @Override
    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }

    @Override
    public List<Person> findPerson(String firstName, String lastName) {
        return personDAO.findPersonByFirstNameAndLastName(firstName, lastName);
    }


    @Override
    public List<CommunityEmailDTO> getCommunityEmailByCity(String city) {
        // Création Liste vide pour sortie
        List<CommunityEmailDTO> listEmailInfo = new ArrayList<>();
        // Récupération de la Liste de toutes les persons du fichier en entrée
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

    @Override
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

    @Override
    public List<ChildAlertDTO> getChildAlertByAddress(String address) {

        List<Person> allPersonInAdress = personDAO.findPersonByAdress(address);

        // Création Liste attendue pour sortie
        List<ChildAlertDTO> listChildInfo = new ArrayList<>();

        for (Person p : allPersonInAdress) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());

            if (medicalRecord != null) {
                int age = calculateFonction.calculateAge(medicalRecord.getBirthdate());

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

    @Override
    public List<FireDTO> getPersonByAddress(String address) {

        List<Person> allPersonInAdress = personDAO.findPersonByAdress(address);

        // Création Liste attendue pour sortie
        List<FireDTO> listPersonInfo = new ArrayList<>();

        for (Person p : allPersonInAdress) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());

            if (medicalRecord != null) {
                int age = calculateFonction.calculateAge(medicalRecord.getBirthdate());

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
            }
        }
        return listPersonInfo;
    }

    @Override
    public List<InfoPersonDetailedDTO> getPersonByFirstLastName(String firstName, String lastName) {

        List<InfoPersonDetailedDTO> InfoPersonDetailed = new ArrayList<>();

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

    @Override
    public Boolean delete(String firstName, String lastName) {
        return personDAO.deletePerson(firstName, lastName);
    }

    @Override
    public Person add(Person person) {
        return personDAO.addPerson(person);
    }

    @Override
    public Person modify(Person person) {
        return personDAO.modifyPerson(person);
    }

}
