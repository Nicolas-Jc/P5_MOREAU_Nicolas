package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.dao.FireStationDAO;
import com.openclassrooms.safetynet.dao.MedicalRecordsDAO;
import com.openclassrooms.safetynet.dao.PersonDAO;
import com.openclassrooms.safetynet.dto.ChildAlertDTO;
import com.openclassrooms.safetynet.dto.CommunityEmailDTO;
import com.openclassrooms.safetynet.dto.PhoneAlertDTO;
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
    private CalculateFonction calculateFonction;


    public List<Person> findAll() {
        return personDAO.getAllPersons();
    }
    /*
    public List<Person> findPersonByAddress(String address) {
        return personDAO.findPersonByAdress(address);
    }*/

    // 1. URL "CommunityEmail"
    // Cette url doit retourner une liste des adresses mail de tous les habitants de la ville saisie.
    public List<CommunityEmailDTO> getCommunityEmailByCity(String city) {
        // Création Liste vide pour sortie
        List<CommunityEmailDTO> listEmailInfo = new ArrayList<>();
        // Récupération de la Liste de toutes les persons du fichier en entrée
        //List<Person> listPersons = findAll();
        List<Person> listPersons = personDAO.getAllPersons();

        System.out.println("Entree PersonService getCommunityEmail");

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

        System.out.println("Entree PersonService getPhoneAlert");

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

    // 3. URL "ChildAlert"
    // Cette url doit retourner une liste d'enfants (tout individu  âgé de 18 ans ou moins)
    // habitant à cette adresse.
    // La liste doit comprendre le prénom et le nom de famille de chaque enfant,
    // son âge et une liste des autres membres du foyer.
    // S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
    public List<ChildAlertDTO> getChildAlertByAddress(String address) {
        // Liste des différentes personnes résidant à l'adresse saisie dans l'URL
        List<Person> allPersonInAdress = personDAO.findPersonByAdress(address);

        // Création Liste attendue pour sortie
        List<ChildAlertDTO> listChildInfo = new ArrayList<>();

        // Boucle sur la liste des personnes résidant à l'adresse saisie
        for (Person p : allPersonInAdress) {
            MedicalRecord medicalRecord = medicalRecordService.findMedicalRecord(p.getFirstName(), p.getLastName());


            if (medicalRecord != null) {
                int age = calculateFonction.calculateAge(medicalRecord.getBirthdate());
                System.out.println(age);

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
}
