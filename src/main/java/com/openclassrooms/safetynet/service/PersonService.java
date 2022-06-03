package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.CommunityEmailDTO;
import com.openclassrooms.safetynet.dto.PhoneAlertDTO;
import com.openclassrooms.safetynet.model.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonDAO personDAO;

    @Autowired
    private FireStationDAO fireStationDAO;


    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }

    // 1. URL "CommunityEmail"
    // Cette url doit retourner une liste des adresses mail de tous les habitants de la ville saisie.
    public List<CommunityEmailDTO> getCommunityEmailByCity(String city) {
        // Création Liste vide pour sortie
        List<CommunityEmailDTO> listEmailInfo = new ArrayList<>();
        // Récupération de la Liste de toutes les persons du fichier en entrée
        List<Person> persons = findAll();

        System.out.println("Entree PersonService getCommunityEmail");

        // Boucle sur chaque occurence de la liste persons (Fichier entrée)
        for (Person p : persons) {
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

        // Liste des différentes adresses desservies par le fireStation
        List<String> listAdress;
        // getFireStationAdressById = fonction
        listAdress = fireStationDAO.getFireStationAdressById(station);

        // Création Liste attendue vide pour sortie
        List<PhoneAlertDTO> listPhoneInfo = new ArrayList<>();
        // Récupération de la Liste de toutes les persons du fichier en entrée
        List<Person> persons = findAll();

        System.out.println("Entree PersonService getPhoneAlert");

        // Boucle sur chaque occurence de la liste persons (Fichier entrée)
        for (Person p : persons) {
            if (listAdress.contains(p.getAddress())) {
                // mise en forme Json sortie
                PhoneAlertDTO phoneAlertDTO = new PhoneAlertDTO();
                phoneAlertDTO.setPhone(p.getPhone());
                listPhoneInfo.add(phoneAlertDTO);
                // A remettre en vigueur seul si blocage ci-dessus
                //listPhone.add(new PhoneAlertDTO(p.getPhone()));
            }
        }
        System.out.println(listPhoneInfo);
        return listPhoneInfo;

    }
/*
    public List<ChildAlertDTO> getChildAlertByAddress(String address) {
        System.out.println("Test");
        return "OK";
    }*/
}
